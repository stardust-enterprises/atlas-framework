package fr.stardustenterprises.atlas.framework.mod.meta

import fr.stardustenterprises.atlas.framework.api.mod.meta.IModEntrypoint

/**
 * Default implementation for [IModEntrypoint].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModEntrypoint(
    override val entryType: String,
    override val className: String,
    override val priority: Int = 0,
): IModEntrypoint
