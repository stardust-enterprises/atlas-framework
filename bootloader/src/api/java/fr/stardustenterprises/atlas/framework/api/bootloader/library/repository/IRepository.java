package fr.stardustenterprises.atlas.framework.api.bootloader.library.repository;

import fr.stardustenterprises.atlas.framework.api.bootloader.library.ILibrary;

import java.nio.file.Path;

/**
 * A repository representation, used to fetch and use {@link ILibrary Libraries}.
 *
 * @param <T> the {@link ILibrary Library} type this repository supports.
 *
 * @author xtrm
 * @since 0.0.1
 */
public interface IRepository<T extends ILibrary> {

    Path fetchLibrary(T library);

}
