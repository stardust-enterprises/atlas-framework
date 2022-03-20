package fr.stardustenterprises.atlas.framework.bootloader.library;

import fr.stardustenterprises.atlas.framework.api.bootloader.classloader.RemappingClassLoader;
import fr.stardustenterprises.atlas.framework.api.bootloader.exceptions.LibraryException;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibrary;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibraryManager;
import fr.stardustenterprises.atlas.framework.api.bootloader.library.repository.IRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Library Management Interface implementation.
 *
 * @author xtrm
 * @since 0.0.1
 */
public class LibraryManager implements ILibraryManager {
    /**
     *
     */
    private final List<ILibrary> libraryList =
        new ArrayList<>();

    private final List<IRepository<? extends ILibrary>> repositoryList =
        new ArrayList<>();

    /**
     * Library {@link ClassLoader}, also called "extended" classloader;
     * it is responsible for loading in memory the framework's core libraries.
     */
    private final RemappingClassLoader libraryClassloader;

    public LibraryManager(RemappingClassLoader libraryClassloader) {
        this.libraryClassloader = libraryClassloader;
    }

    /**
     * Populates the libraries with the provided declaration(s).
     *
     * @param libraryDeclarations library declaration(s).
     * @throws LibraryException if another library
     */
    @Override
    public void populateLibraries(String... libraryDeclarations) throws LibraryException {

    }

    /**
     * Populates the repositories with the provided declaration(s).
     *
     * @param repositoryDeclarations repository declaration(s).
     */
    @Override
    public void populateRepositories(String... repositoryDeclarations) {

    }

    @Override
    public void consumeDependencyDeclaration(URL url) {

    }

    @Override
    public void consumeRepositoryDeclaration(URL url) {

    }

    private void addDependency(String dependencyId) {

    }

    private void addRepository(URL url) {

    }

    /**
     * @return the library {@link ClassLoader}
     */
    @Override
    public RemappingClassLoader getLibraryClassLoader() {
        return this.libraryClassloader;
    }

    /**
     * @return the loaded {@link ILibrary Library} list.
     */
    @Override
    public List<ILibrary> getLibraries() {
        return this.libraryList;
    }

    /**
     * @return the registered {@link IRepository Repository} list.
     */
    @Override
    public List<IRepository<?>> getRepositories() {
        return this.repositoryList;
    }
}
