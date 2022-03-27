package fr.stardustenterprises.atlas.framework.loader.library.repository;

import fr.stardustenterprises.atlas.framework.api.loader.exceptions.LibraryException;
import fr.stardustenterprises.atlas.framework.api.loader.library.maven.IMavenLibrary;
import fr.stardustenterprises.atlas.framework.api.loader.library.repository.IRepository;

import java.io.File;
import java.nio.file.Path;

public class LocalMavenRepository implements IRepository<IMavenLibrary> {
    protected final File root;

    public LocalMavenRepository(File root) {
        this.root = root;
    }

    @Override
    public Path fetchLibrary(IMavenLibrary library) {
        File targetFile = new File(root, getDestinationPath(library));
        if (targetFile.exists()) {
            return targetFile.toPath();
        }
        throw new LibraryException(
            String.format(
                "Library \"%s\" version \"%s\" wasn't found in %s",
                library.getId(),
                library.getVersion(),
                root.getAbsolutePath()
            )
        );
    }

    protected String getDestinationPath(IMavenLibrary library) {
        String fullDeclaration = library.getGroupId().replace(
            '.',
            File.separatorChar
        );
        fullDeclaration += File.separator;
        fullDeclaration += library.getName();
        fullDeclaration += File.separator;
        fullDeclaration += library.getVersion();
        fullDeclaration += File.separator;
        fullDeclaration += getFileName(library);

        return fullDeclaration;
    }

    protected String getFileName(IMavenLibrary library) {
        return library.getName() + '-' + library.getVersion() + ".jar";
    }
}
