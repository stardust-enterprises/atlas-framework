package fr.stardustenterprises.atlas.framework.api.bootloader.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Extension to {@link AtlasClassLoader} with support for
 * class remapping/renaming for defined classes and loaded {@link URL}s.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class RemappingClassLoader extends AtlasClassLoader {
    /**
     * The RemappingClassLoader's {@link Logger} instance.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(RemappingClassLoader.class);

    /**
     * Remapping holder map.
     * TODO(@xtrm)
     */
    private final Map<String, String> remappingMap = new HashMap<>();

    public RemappingClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected byte[] modifyClassBuffer(String className, byte[] classBytes) {
        return super.modifyClassBuffer(className, classBytes);
    }

    /**
     * Adds a remapping rule to this {@link RemappingClassLoader}.
     *
     * @param original The original package/class name.
     * @param newName The remapped package/class name.
     */
    public void remap(String original, String newName) {
        if(original == null || newName == null) {
            throw new IllegalArgumentException("Names cannot be null!");
        }
        this.remappingMap.put(original, newName);
    }
}
