package fr.stardustenterprises.atlas.framework.api.mod

import fr.stardustenterprises.atlas.framework.api.mod.meta.IModMetadata
import java.nio.file.Path

/**
 * A mod container.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface IModContainer {
    /**
     * The mod's metadata.
     */
    val metadata: IModMetadata

    /**
     * The mod's primary instance.
     */
    val instance: Any

    /**
     * The mod's source file.
     */
    val source: Path
}
