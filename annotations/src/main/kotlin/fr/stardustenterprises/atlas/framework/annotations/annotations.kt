package fr.stardustenterprises.atlas.framework.annotations

/**
 * Used to annotate proxy/mapping classes.
 *
 * @param value The possible names for this class
 * @param asRegex Should the values be treated as RegEx (default: false)
 *
 * @author xtrm
 * @since 0.0.1
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Mapping(
    val asRegex: Boolean = false,
    vararg val value: String,
)

/**
 * Used to annotate constructors
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Constructor
