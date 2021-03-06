package fr.stardustenterprises.atlas.framework.annotations

/**
 * Indicates the Mapping class which this member comes from.
 *
 * @param mappingClass The origin mapping class.
 *
 * @author xtrm
 * @since 0.0.1
 */
annotation class MemberOf(
    val mappingClass: String,
)
