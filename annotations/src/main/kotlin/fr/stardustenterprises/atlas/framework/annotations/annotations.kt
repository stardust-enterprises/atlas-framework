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
@Target(
    AnnotationTarget.TYPE,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION
)
annotation class NamedProxy(
    val asRegex: Boolean = false,
    vararg val value: String,
)
