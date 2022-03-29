package fr.stardustenterprises.atlas.framework.api.loader.library;

import fr.stardustenterprises.atlas.framework.api.loader.classloader.RemappingClassLoader;
import fr.stardustenterprises.atlas.framework.api.loader.exception.LibraryException;
import fr.stardustenterprises.atlas.framework.api.loader.library.repository.IRepository;

import java.net.URL;
import java.util.List;

/**
 * The library management interface.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface ILibraryManager {
    /**
     * Populates the libraries with the provided declaration(s).
     *
     * @param libraryDeclarations The library declaration(s).
     * @throws LibraryException TODO(@xtrm-en)
     */
    void populateLibraries(String... libraryDeclarations)
        throws LibraryException;

    /**
     * Populates the repositories with the provided declaration(s).
     *
     * @param repositoryDeclarations Repository declaration(s).
     */
    void populateRepositories(String... repositoryDeclarations);

    void consumeDependencyDeclaration(URL url);

    void consumeRepositoryDeclaration(URL url);

    /**
     * @return The library {@link ClassLoader}.
     */
    RemappingClassLoader getLibraryClassLoader();

    /**
     * @return The loaded {@link ILibrary Library} list.
     */
    List<ILibrary> getLibraries();

    /**
     * @return The registered {@link IRepository Repository} list.
     */
    List<IRepository<?>> getRepositories();
}
