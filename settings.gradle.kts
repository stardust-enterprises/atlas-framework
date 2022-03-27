rootProject.name = "atlas-framework"

pluginManagement.repositories {
    mavenLocal()
    gradlePluginPortal()
}

include("annotations")
include("loader")
include("engine")
include("bootstrap")
include("loader-native")
