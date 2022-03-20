rootProject.name = "atlas-framework"

pluginManagement.repositories {
    mavenLocal()
    gradlePluginPortal()
}
include("annotations")
include("bootloader")
include("init-injection")
include("init-classpath")
