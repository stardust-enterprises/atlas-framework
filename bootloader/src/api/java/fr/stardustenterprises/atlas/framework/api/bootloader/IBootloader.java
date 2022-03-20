package fr.stardustenterprises.atlas.framework.api.bootloader;

import fr.stardustenterprises.atlas.framework.api.bootloader.classloader.AtlasClassLoader;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibraryManager;

/**
 * The base Bootloader interface.
 */
public interface IBootloader {
    /**
     * The entrypoint initialization method.<br>
     * <br>
     * Goals of this method:
     * <ul>
     *     <li>initialize the {@link ILibraryManager LibraryManager} and populate the Library {@link ClassLoader}</li>
     *     <li>initialize RetroLoader</li>
     * </ul>
     */
    void initialize();

    /**
     * @return the {@link ILibraryManager}
     */
    ILibraryManager getLibraryManager();

    /**
     * @return the framework's {@link ClassLoader}
     */
    AtlasClassLoader getFrameworkClassLoader();

}
