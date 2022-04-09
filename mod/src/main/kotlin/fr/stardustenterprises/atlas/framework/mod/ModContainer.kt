package fr.stardustenterprises.atlas.framework.mod

import fr.stardustenterprises.atlas.framework.api.mod.IModContainer
import fr.stardustenterprises.atlas.framework.api.mod.meta.IModMetadata
import java.nio.file.Path

/**
 * Default implementation for [IModContainer].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModContainer(
    override val metadata: IModMetadata,
    override val instance: Any,
    override val source: Path,
) : IModContainer
