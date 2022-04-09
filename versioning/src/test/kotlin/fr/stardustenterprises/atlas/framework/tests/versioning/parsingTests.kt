package fr.stardustenterprises.atlas.framework.tests.versioning

import fr.stardustenterprises.atlas.framework.versioning.toSemVer
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

val validSementicVersions = arrayOf(
    "0.0.4",
    "1.2.3",
    "10.20.30",
    "1.1.2-prerelease+meta",
    "1.1.2+meta",
    "1.1.2+meta-valid",
    "1.0.0-alpha",
    "1.0.0-beta",
    "1.0.0-alpha.beta",
    "1.0.0-alpha.beta.1",
    "1.0.0-alpha.1",
    "1.0.0-alpha0.valid",
    "1.0.0-alpha.0valid",
    "1.0.0-alpha-a.b-c-somethinglong+build.1-aef.1-its-okay",
    "1.0.0-rc.1+build.1",
    "2.0.0-rc.1+build.123",
    "1.2.3-beta",
    "10.2.3-DEV-SNAPSHOT",
    "1.2.3-SNAPSHOT-123",
    "1.0.0",
    "2.0.0",
    "1.1.7",
    "2.0.0+build.1848",
    "2.0.1-alpha.1227",
    "1.0.0-alpha+beta",
    "1.2.3----RC-SNAPSHOT.12.9.1--.12+788",
    "1.2.3----R-S.12.9.1--.12+meta",
    "1.2.3----RC-SNAPSHOT.12.9.1--.12",
    "1.0.0+0.build.1-rc.10000aaa-kk-0.1",
    "99999999999999999999999.999999999999999999.99999999999999999",
    "1.0.0-0A.is.legal",
)

val invalidSementicVersions = arrayOf(
    "1",
    "1.2",
    "1.2.3-0123",
    "1.2.3-0123.0123",
    "1.1.2+.123",
    "+invalid",
    "-invalid",
    "-invalid+invalid",
    "-invalid.01",
    "alpha",
    "alpha.beta",
    "alpha.beta.1",
    "alpha.1",
    "alpha+beta",
    "alpha_beta",
    "alpha.",
    "alpha..",
    "beta",
    "1.0.0-alpha_beta",
    "-alpha.",
    "1.0.0-alpha..",
    "1.0.0-alpha..1",
    "1.0.0-alpha...1",
    "1.0.0-alpha....1",
    "1.0.0-alpha.....1",
    "1.0.0-alpha......1",
    "1.0.0-alpha.......1",
    "01.1.1",
    "1.01.1",
    "1.1.01",
    "1.2",
    "1.2.3.DEV",
    "1.2-SNAPSHOT",
    "1.2.31.2.3----RC-SNAPSHOT.12.09.1--..12+788",
    "1.2-RC-SNAPSHOT",
    "-1.0.3-gamma+b7718",
    "+justmeta",
    "9.8.7+meta+meta",
    "9.8.7-whatever+meta+meta",
    "99999999999999999999999.999999999999999999.99999999999999999----RC-SNAPSHOT.12.09.1--------------------------------..12",
)

internal class ParsingTests {
    @Test
    fun `parse valid semantic versions`() {
        validSementicVersions.forEach {
            try {
                it.toSemVer()
            } catch (e: Exception) {
                throw AssertionError("Parsing $it should not throw an exception", e)
            }
        }
    }

    @Test
    fun `parse invalid semantic versions`() {
        invalidSementicVersions.forEach {
            assertFailsWith<IllegalArgumentException> {
                it.toSemVer()
            }
        }
    }

    @Test
    fun `simple range check`() {
        val lowerEnd = "1.0.8".toSemVer()
        val upperEnd = "2.0.0".toSemVer()
        val range = lowerEnd..upperEnd

        // bounds
        assertTrue(lowerEnd in range)
        assertTrue(upperEnd in range)

        assertTrue(range.contains("1.0.8".toSemVer()))
        assertTrue(range.contains("1.0.9".toSemVer()))
        assertTrue(range.contains("1.1.0".toSemVer()))
        assertTrue(range.contains("1.2.0".toSemVer()))
        assertTrue(range.contains("2.0.0".toSemVer()))
        assertFalse(range.contains("2.0.1".toSemVer()))
        assertFalse(range.contains("2.0.2".toSemVer()))
    }

    @Test
    fun `preRelease range check`() {
        val lowerEnd = "1.0.8-beta".toSemVer()
        val upperEnd = "2.0.0".toSemVer()
        val range = lowerEnd..upperEnd

        // bounds
        assertTrue(lowerEnd in range)
        assertTrue(upperEnd in range)

        assertTrue(range.contains("1.0.8-beta".toSemVer()))
        assertTrue(range.contains("1.0.8".toSemVer()))
        assertTrue(range.contains("2.0.0-alpha".toSemVer()))
        assertFalse(range.contains("1.0.8-alpha".toSemVer()))
    }

    @Test
    fun `build range check`() {
        val lowerEnd = "1.0.8".toSemVer()
        val upperEnd = "2.0.0".toSemVer()
        val range = lowerEnd..upperEnd

        assertTrue(range.contains("1.0.8+build.1".toSemVer()))
        assertTrue(range.contains("1.0.8+build.2".toSemVer()))

        assertFalse(range.contains("1.0.7+build.34".toSemVer()))
    }
}
