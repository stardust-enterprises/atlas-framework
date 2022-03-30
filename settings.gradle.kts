rootProject.name = "atlas-framework"

pluginManagement.repositories {
    mavenLocal()
    gradlePluginPortal()
}

include("annotations")
include("bootstrap")
include("bootstrap.native")
include("engine")
include("loader")
include("linker")
include("linker.analysis")
