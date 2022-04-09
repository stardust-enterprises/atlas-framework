package fr.stardustenterprises.atlas.framework.api.mod.meta

import fr.stardustenterprises.atlas.framework.versioning.SemanticVersion
import fr.stardustenterprises.atlas.framework.versioning.toSemVer

/**
 * Representation of a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface IModMetadata {
    /**
     * The mod's identifier.
     */
    val id: String

    /**
     * The mod's display name.
     */
    val displayName: String?

    /**
     * The mod's description.
     */
    val description: String?

    /**
     * The mod's current version string.
     */
    val version: String

    /**
     * The mod's authors.
     */
    val authors: List<IModAuthor>
        get() = emptyList()

    /**
     * The mod's contact information.
     */
    val contact: IModContact?

    /**
     * The mod's license.
     *
     * May be provided in the [IModContact.repository]
     * by setting this value to `repo`.
     */
    val licence: String?

    /**
     * Mod entrypoint declarations.
     */
    val entrypoints: List<IModEntrypoint>
        get() = emptyList()

    /**
     * @return a [SemanticVersion] object.
     */
    fun getSemanticVersion(): SemanticVersion =
        this.version.toSemVer()
}
