private const val kotlinVersion = "1.6.10"

object Plugins {
    const val KOTLIN = kotlinVersion
    const val GRGIT = "4.1.1" // old version for jgit to work on Java 8
    const val BLOSSOM = "1.3.0"
    const val SHADOW = "7.1.2"
    const val KTLINT = "10.2.1"
    const val DOKKA = kotlinVersion
    const val NEXUS_PUBLISH = "1.0.0"
}

object Dependencies {
    const val KOTLIN = kotlinVersion

    // Bootloader
    const val LOGBACK = "1.2.6"
    const val SLF4J = "1.7.30"
    const val BOOTCLASSLOADER = "0.1.0"
    const val DEFACE = "0.2.0"
    const val RETROLOADER = "0.1.0"

    // init-classpath
    const val LAUNCHWRAPPER = "1.12"

    val kotlinModules = arrayOf("stdlib")
}

object Repositories {
    val mavenUrls: Array<String> =
        arrayOf(
//            "https://jitpack.io/",
        )
}
