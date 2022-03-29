package fr.stardustenterprises.atlas.framework.api.loader.library.maven;

import fr.stardustenterprises.atlas.framework.api.loader.library.ILibrary;

import java.util.regex.Pattern;

/**
 * @author xtrm
 * @since 0.0.1
 */
public interface IMavenLibrary extends ILibrary {
    default String getGroupId() {
        return getId().split(Pattern.quote(":"))[0];
    }

    default String getName() {
        return getId().split(Pattern.quote(":"))[1];
    }
}
