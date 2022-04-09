package fr.stardustenterprises.atlas.framework.api.mod

import fr.stardustenterprises.atlas.framework.versioning.SemanticVersion

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
     * The mod's name.
     */
    val name: String?

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
     * The related Git/source repository URL, as a [String].
     */
    val repository: String?

    /**
     * Mod entrypoint declarations.
     */
    val entrypoints: List<IModEntrypoint>
        get() = emptyList()

    /**
     * @return a [SemanticVersion] object.
     */
    fun getSemVersion(): SemanticVersion
}
