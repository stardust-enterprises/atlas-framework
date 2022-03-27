package fr.stardustenterprises.atlas.framework.api.loader.library;

/**
 * Abstract library representation.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface ILibrary {
    /**
     * @return this library's unique identifier.
     */
    String getId();

    /**
     * @return this library's version.
     */
    String getVersion();
}
