package fr.stardustenterprises.atlas.framework.api.loader.library;

/**
 * An abstract library representation.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface ILibrary {
    /**
     * @return This library's unique identifier.
     */
    String getId();

    /**
     * @return This library's version.
     */
    String getVersion();
}
