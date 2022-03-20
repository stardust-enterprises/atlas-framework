package fr.stardustenterprises.atlas.framework.bootloader;

import fr.stardustenterprises.atlas.framework.api.bootloader.IBootloader;
import fr.stardustenterprises.atlas.framework.api.bootloader.classloader.AtlasClassLoader;
import fr.stardustenterprises.atlas.framework.api.bootloader.classloader.RemappingClassLoader;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibraryManager;
import fr.stardustenterprises.atlas.framework.bootloader.library.LibraryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Atlas Framework Bootloader.
 */
public class Bootloader implements IBootloader {
    /**
     * The Bootloader's {@link Logger} instance.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(Bootloader.class);

    /**
     * Library {@link ClassLoader}, also called "extended" classloader;
     * it is responsible for loading in memory the framework's core libraries.
     */
    private final RemappingClassLoader libraryClassLoader =
        new RemappingClassLoader(Bootloader.class.getClassLoader());

    /**
     * Framework {@link ClassLoader}, also called "platform" classloader;
     * it is responsible for loading in memory the framework's modules.
     */
    private final AtlasClassLoader frameworkClassLoader =
        new AtlasClassLoader(libraryClassLoader);

    /**
     * The {@link ILibraryManager LibraryManager} interface;
     * used to dynamically download and inject library in the JVM.
     */
    private final ILibraryManager libraryManager =
        new LibraryManager(this.libraryClassLoader);

    /**
     * @inheritDoc
     */
    @Override
    public void initialize() {
        this.libraryManager.populateLibraries("boot");
    }

    /**
     * @inheritDoc
     */
    @Override
    public ILibraryManager getLibraryManager() {
        return this.libraryManager;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AtlasClassLoader getFrameworkClassLoader() {
        return this.frameworkClassLoader;
    }
}
