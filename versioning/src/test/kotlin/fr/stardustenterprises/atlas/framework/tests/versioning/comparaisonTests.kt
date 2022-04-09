package fr.stardustenterprises.atlas.framework.tests.versioning

import fr.stardustenterprises.atlas.framework.versioning.toSemVer
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class ComparaisonTests {
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
