package fr.stardustenterprises.atlas.framework.loader.library.repository;

import fr.stardustenterprises.atlas.framework.api.loader.exception.LibraryException;
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
        if (!targetFile.exists()) {
            throw new LibraryException(
                String.format(
                    "Library \"%s\" version \"%s\" wasn't found in %s",
                    library.getId(),
                    library.getVersion(),
                    root.getAbsolutePath()
                )
            );
        }

        return targetFile.toPath();
    }

    protected String getDestinationPath(IMavenLibrary library) {
        return library.getGroupId().replace('.', File.separatorChar)
            + File.separator
            + library.getName()
            + File.separator
            + library.getVersion()
            + File.separator
            + getFileName(library);
    }

    protected String getFileName(IMavenLibrary library) {
        return library.getName() + '-' + library.getVersion() + ".jar";
    }
}
