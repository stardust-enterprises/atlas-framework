package fr.stardustenterprises.atlas.framework.api.loader.library.repository;

import fr.stardustenterprises.atlas.framework.api.loader.library.ILibrary;

import java.nio.file.Path;

/**
 * A repository representation, used to fetch and use
 * {@link ILibrary Libraries}.
 *
 * @param <T> The {@link ILibrary Library} type this repository supports.
 * @author xtrm
 * @since 0.0.1
 */
public interface IRepository<T extends ILibrary> {
    Path fetchLibrary(T library);
}
