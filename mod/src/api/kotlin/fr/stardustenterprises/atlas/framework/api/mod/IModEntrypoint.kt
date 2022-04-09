package fr.stardustenterprises.atlas.framework.api.mod

/**
 * An entrypoint for a mod.
 *
 * @author xtrm
 * @since 0.0.1
 */
interface IModEntrypoint {
    /**
     * The entrypoint type.
     */
    val entryType: String

    /**
     * The entrypoint's priority.
     */
    val priority: Int
        get() = 0

    /**
     * The entrypoint's class name.
     */
    val className: String
}
