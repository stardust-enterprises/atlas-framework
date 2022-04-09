package fr.stardustenterprises.atlas.framework.mod.meta

import fr.stardustenterprises.atlas.framework.api.mod.meta.IModAuthor
import fr.stardustenterprises.atlas.framework.api.mod.meta.IModContact
import fr.stardustenterprises.atlas.framework.api.mod.meta.IModEntrypoint
import fr.stardustenterprises.atlas.framework.api.mod.meta.IModMetadata

/**
 * Default implementation for [IModMetadata].
 *
 * @author xtrm
 * @since 0.0.1
 */
data class ModMetadata(
    override val id: String,
    override val displayName: String? = null,
    override val description: String? = null,
    override val version: String,
    override val authors: List<IModAuthor> = emptyList(),
    override val contact: IModContact = ModContact(),
    override val licence: String? = null,
    override val entrypoints: List<IModEntrypoint> = emptyList()
) : IModMetadata
