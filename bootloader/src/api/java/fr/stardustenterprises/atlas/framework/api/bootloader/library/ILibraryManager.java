package fr.stardustenterprises.atlas.framework.api.bootloader.library;

import fr.stardustenterprises.atlas.framework.api.bootloader.classloader.RemappingClassLoader;
import fr.stardustenterprises.atlas.framework.api.bootloader.exceptions.LibraryException;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.repository.IRepository;

import java.net.URL;
import java.util.List;

/**
 * Library Management Interface.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface ILibraryManager {

    /**
     * Populates the libraries with the provided declaration(s).
     *
     * @param libraryDeclarations library declaration(s).
     *
     * @throws LibraryException if another library
     */
    void populateLibraries(String... libraryDeclarations)
        throws LibraryException;

    /**
     * Populates the repositories with the provided declaration(s).
     *
     * @param repositoryDeclarations repository declaration(s).
     */
    void populateRepositories(String... repositoryDeclarations);

    void consumeDependencyDeclaration(URL url);

    void consumeRepositoryDeclaration(URL url);

    /**
     * @return the library {@link ClassLoader}
     */
    RemappingClassLoader getLibraryClassLoader();

    /**
     * @return the loaded {@link ILibrary Library} list.
     */
    List<ILibrary> getLibraries();

    /**
     * @return the registered {@link IRepository Repository} list.
     */
    List<IRepository<?>> getRepositories();

}
