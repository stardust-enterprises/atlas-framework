package fr.stardustenterprises.atlas.framework.init;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ServiceLoader;

/**
 * Payload to initialize Atlas Framework from an injection context.
 *
 * @author xtrm
 * @since 0.0.1
 */
enum InjectionInitializer {
    ;
    private static final String IBOOTLOADER_CLASS =
        "fr.stardustenterprises.atlas.framework.api.bootloader.IBootloader";

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
        if(!bootloaderFile.exists()) {
            throw new RuntimeException("Bootloader module doesn't exist!");
        }

        // load bootloader
        appendToBootClassloader(bootloaderFile.getAbsolutePath());

        // call bootloader
        Class<?> bootloaderInterfaceClass =
            Class.forName(IBOOTLOADER_CLASS, false, null);
        ServiceLoader<?> serviceLoader = ServiceLoader.load(bootloaderInterfaceClass);
        Object bootloaderInstance = serviceLoader.iterator().next();
        Class<?> bootloaderClass = bootloaderInstance.getClass();
        Method m_initialize =
            bootloaderClass.getDeclaredMethod("initialize");
        m_initialize.setAccessible(true);
        m_initialize.invoke(bootloaderInstance);
    }

    private static String getDefaultDataDir() {
        return null;
    }

    /**
     * Internal method used to inject into the boot {@link ClassLoader}
     * the framework's Bootloader.
     *
     * @param absolutePath The jarfile's absolute path.
     */
    private static native void appendToBootClassloader(String absolutePath);

    static {
        init();
    }
}
