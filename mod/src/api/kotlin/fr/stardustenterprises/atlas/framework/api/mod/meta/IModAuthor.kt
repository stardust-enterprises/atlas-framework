package fr.stardustenterprises.atlas.framework.api.mod.meta

/**
 * Represents the author of a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface IModAuthor {
    /**
     * The name of the author.
     */
    val name: String

    /**
     * The link to the author's Git profile.
     */
    val gitProfile: String?

    /**
     * The email address of the author.
     */
    val mail: String?

    /**
     * The Minecraft profile UUID of the author, as a [String].
     */
    val uuid: String?
}
