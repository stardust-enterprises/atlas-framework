package fr.stardustenterprises.atlas.framework.bootstrap;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

/**
 * Payload to initialize Atlas Framework from an injection context.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class RuntimeEntrypoint {
    private static final String ILOADER_CLASS =
        "fr.stardustenterprises.atlas.framework.api.loader.ILoader";

    private static boolean firstRun = true;

    public static void init() {
        try {
            init0();
        } catch (Throwable throwable) {
            new RuntimeException(
                "An error occured while initializing Atlas Framework."
            ).printStackTrace();
        }
    }

    private static void init0() throws ReflectiveOperationException {
        boolean isFirstRun = firstRun;
        firstRun = false;

        // find bootloader
        // atlasDir / modules / bootloader.jar ?
        String atlasDataDir =
            System.getProperty("atlas.init.datadir", getDefaultDataDir());

        File bootloaderFile = new File(
            atlasDataDir,
            "modules" + File.separator + "bootloader.jar"
        );

        if (!bootloaderFile.exists()) {
            throw new RuntimeException("Bootloader module doesn't exist!");
        }

        // load bootloader
        appendToClassLoader0(bootloaderFile.getAbsolutePath());

        // call bootloader
        Class<?> bootloaderInterfaceClass =
            Class.forName(ILOADER_CLASS, false, null);

        ServiceLoader<?> serviceLoader =
            ServiceLoader.load(bootloaderInterfaceClass);

        Object bootloaderInstance = serviceLoader.iterator().next();
        Method initializeMethod =
            bootloaderInstance.getClass().getDeclaredMethod("initialize");

        initializeMethod.setAccessible(true);
        initializeMethod.invoke(bootloaderInstance);
    }

    /**
     * TODO(@xtrm-en)
     */
    private static String getDefaultDataDir() {
        return null;
    }

    /**
     * Internal method used to inject into the boot {@link ClassLoader}
     * the framework's Bootloader.
     * <p>
     * Note: this method is linked to the native library at runtime via JNI.
     *
     * @param absolutePath The jarfile's absolute path.
     */
    private static native void appendToClassLoader0(String absolutePath);
}
