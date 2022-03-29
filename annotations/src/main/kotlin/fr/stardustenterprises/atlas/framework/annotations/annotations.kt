package fr.stardustenterprises.atlas.framework.annotations

/**
 * Used to annotate proxy/mapping classes.
 *
 * @param value The possible names for this class.
 * @param asRegex Whether the values be treated as a regular expression.
 *
 * @author xtrm
 * @since 0.0.1
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Mapping(
    vararg val value: String,
    val asRegex: Boolean = false,
)

/**
 * Used to annotate constructors.
 *
 * @author xtrm
 * @since 0.0.1
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CONSTRUCTOR)
annotation class Constructor
