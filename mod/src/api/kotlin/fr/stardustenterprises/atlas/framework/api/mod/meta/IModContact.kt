package fr.stardustenterprises.atlas.framework.api.mod.meta

/**
 * Contact Information for a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface IModContact {
    /**
     * The mod's website URL, as a [String].
     */
    val website: String?

    /**
     * The mod's source repository URL, as a [String].
     */
    val repository: String?

    /**
     * The mod's issue tracker URL, as a [String].
     */
    val issues: String?

    /**
     * The mod's discord server URL, as a [String].
     */
    val discord: String?
}
