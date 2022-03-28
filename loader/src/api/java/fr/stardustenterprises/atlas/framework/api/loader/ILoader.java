package fr.stardustenterprises.atlas.framework.api.loader;

import fr.stardustenterprises.atlas.framework.api.loader.classloader.AtlasClassLoader;
import fr.stardustenterprises.atlas.framework.api.loader.library.ILibraryManager;

/**
 * The base Loader interface.
 */
public interface ILoader {
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
