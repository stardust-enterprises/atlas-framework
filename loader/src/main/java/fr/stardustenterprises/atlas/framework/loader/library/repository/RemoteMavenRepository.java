package fr.stardustenterprises.atlas.framework.loader.library.repository;

import fr.stardustenterprises.atlas.framework.api.loader.exception.LibraryException;
import fr.stardustenterprises.atlas.framework.api.loader.library.maven.IMavenLibrary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.Arrays;

public class RemoteMavenRepository extends LocalMavenRepository {
    private final URL[] remoteRepositories;

    public RemoteMavenRepository(File root, URL[] remoteRepositories) {
        super(root);
        this.remoteRepositories = remoteRepositories;
    }

    @Override
    public Path fetchLibrary(IMavenLibrary library) {
        URL targetUrl = findFirstURL(library);
        if (targetUrl == null) {
            throw new LibraryException(
                String.format(
                    "Library \"%s\" version \"%s\" wasn't found on: %s",
                    library.getId(),
                    library.getVersion(),
                    Arrays.toString(this.remoteRepositories)
                )
            );
        }

        File targetFile = new File(root, this.getDestinationPath(library));
        downloadUrl(targetUrl, targetFile);

        return super.fetchLibrary(library);
    }

    protected URL findFirstURL(IMavenLibrary library) {
        //for (URL repoUrl : this.remoteRepositories) {
        //    TODO(@xtrm-en)
        //}
        return null;
    }

    protected void downloadUrl(URL url, File target) {
        try {
            ReadableByteChannel sourceChannel =
                Channels.newChannel(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(target);
            FileChannel targetChannel = fileOutputStream.getChannel();

            targetChannel.transferFrom(sourceChannel, 0, Long.MAX_VALUE);
        } catch (IOException ioException) {
            throw new LibraryException("Couldn't download library \"%s\"", ioException);
        }
    }
}
