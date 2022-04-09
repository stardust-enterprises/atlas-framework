package fr.stardustenterprises.atlas.framework.mod.meta

import fr.stardustenterprises.atlas.framework.api.mod.meta.IModContact

/**
 * Default implementation for [IModContact].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModContact(
    override val website: String? = null,
    override val repository: String? = null,
    override val issues: String? = null,
    override val discord: String? = null,
) : IModContact
