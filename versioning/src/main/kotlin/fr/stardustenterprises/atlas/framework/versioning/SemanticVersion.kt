package fr.stardustenterprises.atlas.framework.versioning

import java.math.BigInteger

/**
 * An implementation of the Semantic Versioning specification.
 *
 * **See**: [https://semver.org/](https://semver.org/)
 *
 * @author xtrm
 * @since 0.0.1
 */
data class SemanticVersion(
    val major: BigInteger,
    val minor: BigInteger,
    val patch: BigInteger,
    val preRelease: String? = null,
    val build: String? = null,
) : Comparable<SemanticVersion> {
    companion object {
        /**
         * Regex pattern for matching a Semantic Version.
         */
        private val SEMVER_REGEX =
            Regex("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?\$")

        /**
         * Parses a [SemanticVersion] from a string.
         *
         * @param version The string to parse.
         * @return The parsed [SemanticVersion].
         * @throws IllegalArgumentException If the string is not a valid Semantic Version.
         */
        @JvmStatic
        fun from(version: String): SemanticVersion = run {
            if (!SEMVER_REGEX.matches(version))
                throw IllegalArgumentException("Invalid version string: $version")

            val matches = SEMVER_REGEX.find(version)!!.groupValues
            val major = matches[1].toBigInteger()
            val minor = matches[2].toBigInteger()
            val patch = matches[3].toBigInteger()
            val preRelease = matches[4]
            val build = matches[5]

            SemanticVersion(
                major,
                minor,
                patch,
                preRelease.ifBlank { null },
                build.ifBlank { null }
            )
        }
    }

    override fun compareTo(other: SemanticVersion): Int = when {
        major != other.major -> major.compareTo(other.major)
        minor != other.minor -> minor.compareTo(other.minor)
        patch != other.patch -> patch.compareTo(other.patch)
        preRelease != other.preRelease -> {
            if (preRelease == null) 1
            else if (other.preRelease == null) -1
            else preRelease.compareTo(other.preRelease)
        }
        build != other.build -> {
            if (build == null) 1
            else if (other.build == null) -1
            else build.compareTo(other.build)
        }
        else -> 0
    }

    /**
     * @return The string representation of this [SemanticVersion].
     */
    override fun toString(): String =
        "$major.$minor.$patch" +
            (preRelease?.let { "-$it" } ?: "") +
            (build?.let { "+$it" } ?: "")
}
