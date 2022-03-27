package fr.stardustenterprises.atlas.framework.loader.annotations

/**
 * Indicates the Mapping class that this member comes from
 *
 * @param mappingClass The origin mapping class
 *
 * @author xtrm
 * @since 0.0.1
 */
annotation class MemberOf(
    val mappingClass: String,
)
