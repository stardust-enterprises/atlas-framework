package fr.stardustenterprises.atlas.framework.api.loader.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Base {@link ClassLoader} class for all of Atlas Framework's loaders.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class AtlasClassLoader extends URLClassLoader {
    /**
     * The AtlasClassLoader's {@link Logger} instance.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(RemappingClassLoader.class);

    /**
     * This {@link ClassLoader}'s parent loader.
     */
    private final ClassLoader parent;

    /**
     * Correctly parsed and defined classes cache.
     * <p>
     * (className) -> Class
     */
    private final Map<String, Class<?>> classCache = new ConcurrentHashMap<>();

    /**
     * Correctly found and read resources cache.
     * <p>
     * (resourcePath) -> byte[]
     */
    private final Map<URL, byte[]> resourceCache = new ConcurrentHashMap<>();

    /**
     * Unknown/not found classes names.
     */
    private final Set<String> invalidClasses = new HashSet<>();

    /**
     * Unknown/not found resources URLs.
     */
    private final Set<URL> invalidResources = new HashSet<>();

    /**
     * List of packages/classes to delegate loading to the parent
     * {@link ClassLoader}.
     */
    private final Set<String> ignorePackages = new HashSet<>();

    public AtlasClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
        this.parent = parent;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        for (String ignored : this.ignorePackages) {
            if (name.startsWith(ignored)) {
                return this.parent.loadClass(name);
            }
        }

        if (!this.invalidClasses.contains(name)) {
            Class<?> clazz = this.classCache.computeIfAbsent(
                name, className -> {
                    String path = className.replace('.', '/')
                        + ".class";

                    URL url = findResource(path);
                    if (url == null) {
                        return null;
                    }

                    byte[] classBytes = getClassBytes(url);
                    if (classBytes == null) {
                        return null;
                    }

                    URLConnection connection = connect(url);
                    CodeSigner[] signers = null;

                    int index = className.lastIndexOf('.');
                    if (index > -1) {
                        try {
                            String packageName = className.substring(0, index);

                            if (connection instanceof JarURLConnection jarURLConnection) {
                                JarFile jarFile = jarURLConnection.getJarFile();

                                if (
                                    jarFile != null &&
                                        jarFile.getManifest() != null
                                ) {
                                    Manifest manifest = jarFile.getManifest();
                                    JarEntry entry = jarFile.getJarEntry(path);

                                    Package pkg = getPackage(packageName);
                                    signers = entry.getCodeSigners();
                                    if (pkg == null) {
                                        definePackage(
                                            packageName,
                                            manifest,
                                            jarURLConnection.getJarFileURL()
                                        );
                                    }
                                }
                            } else {
                                Package pkg = getPackage(packageName);
                                if (pkg == null) {
                                    definePackage(
                                        packageName,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null,
                                        null
                                    );
                                }
                            }
                        } catch (IOException ignored) {
                        }
                    }

                    byte[] modifiedClassBuffer =
                        modifyClassBuffer(className, classBytes);

                    try {
                        CodeSource codeSource = connection == null
                            ? null
                            : new CodeSource(connection.getURL(), signers);
                        return defineClass(
                            className,
                            modifiedClassBuffer,
                            0,
                            modifiedClassBuffer.length,
                            codeSource
                        );
                    } catch (Throwable throwable) {
                        return null;
                    }
                }
            );

            if (clazz != null) {
                return clazz;
            }
        }

        this.invalidClasses.add(name);
        throw new ClassNotFoundException(name);
    }

    /**
     * Defines a {@link Class} on this {@link ClassLoader}.
     *
     * @param className   The class' name.
     * @param classBuffer The class' bytecode.
     * @return The defined {@link Class}.
     * @throws ClassFormatError if the class buffer is invalid.
     */
    public Class<?> defineClass(
        String className,
        byte[] classBuffer
    ) {
        return this.defineClass(
            className,
            classBuffer,
            0,
            classBuffer.length
        );
    }

    /**
     * Append {@link URL}s to this classloader's search path.
     *
     * @param urls The URLs to append.
     */
    public void addURLs(URL... urls) {
        Arrays.stream(urls)
            .filter(Objects::nonNull)
            .forEach(this::addURL);
    }

    /**
     * Adds a package/class path to the list of "ignored"/delegated packages to
     * the parent {@link ClassLoader}.
     *
     * @param path The package/class path.
     */
    public void addIgnoredPaths(String path) {
        this.ignorePackages.add(path);
    }

    /**
     * Prunes the {@link AtlasClassLoader#invalidClasses} set.
     */
    public void pruneInvalidClasses() {
        this.pruneInvalidClasses(s -> true);
    }

    /**
     * Prunes the {@link AtlasClassLoader#invalidClasses} set according to the
     * supplied predicate.
     *
     * @param predicate The {@link Predicate<String>} to test against class
     *                  names.
     */
    public void pruneInvalidClasses(Predicate<? super String> predicate) {
        this.invalidClasses.removeIf(predicate);
    }

    /**
     * Prunes the {@link AtlasClassLoader#invalidResources} set.
     */
    public void pruneInvalidResources() {
        this.pruneInvalidResources(s -> true);
    }

    /**
     * Prunes the {@link AtlasClassLoader#invalidResources} set according to
     * the supplied predicate.
     *
     * @param predicate The {@link Predicate<URL>} to test against resource
     *                  URLs.
     */
    public void pruneInvalidResources(Predicate<? super URL> predicate) {
        this.invalidResources.removeIf(predicate);
    }

    /**
     * Allow for modification of classfile buffers.
     * <p>
     * This method should be implemented in
     * this {@link ClassLoader}'s subclasses.
     *
     * @param className  The class' name.
     * @param classBytes The class' bytecode.
     */
    protected byte[] modifyClassBuffer(String className, byte[] classBytes) {
        return classBytes;
    }

    private byte[] getClassBytes(URL url) {
        if (!this.invalidResources.contains(url)) {
            byte[] classBytes = this.resourceCache.computeIfAbsent(
                url, classURL -> {
                    InputStream inputStream;
                    try {
                        inputStream = url.openStream();

                        ByteArrayOutputStream outputStream =
                            new ByteArrayOutputStream();

                        int temp;
                        byte[] data = new byte[4];

                        while (
                            (
                                temp = inputStream.read(
                                    data,
                                    0,
                                    data.length
                                )
                            ) != -1
                        ) {
                            outputStream.write(data, 0, temp);
                        }

                        outputStream.flush();
                        byte[] byteArray = outputStream.toByteArray();
                        outputStream.close();
                        return byteArray;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                });

            if (classBytes != null) {
                return classBytes;
            }
        }

        this.invalidResources.add(url);
        return null;
    }

    private URLConnection connect(URL url) {
        try {
            return url.openConnection();
        } catch (Exception ignored) {
        }

        return null;
    }
}
