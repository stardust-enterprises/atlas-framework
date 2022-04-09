package fr.stardustenterprises.atlas.framework.mod

import fr.stardustenterprises.atlas.framework.api.mod.IModAuthor

/**
 * Default implementation for [IModAuthor].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModAuthor(
    override val name: String,
    override val gitProfile: String? = null,
    override val mail: String? = null,
    override val uuid: String? = null
): IModAuthor
