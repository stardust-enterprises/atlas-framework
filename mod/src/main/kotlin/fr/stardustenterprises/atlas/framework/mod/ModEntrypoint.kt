package fr.stardustenterprises.atlas.framework.mod

import fr.stardustenterprises.atlas.framework.api.mod.IModAuthor
import fr.stardustenterprises.atlas.framework.api.mod.IModEntrypoint

/**
 * Default implementation for [IModAuthor].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModEntrypoint(
    override val entryType: String,
    override val className: String,
    override val priority: Int = 0,
): IModEntrypoint
