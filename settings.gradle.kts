rootProject.name = "atlas-framework"

pluginManagement.repositories {
    mavenLocal()
    gradlePluginPortal()
}

include("annotations")
include("bootstrap")
include("bootstrap.native")
include("engine")
include("engine.mod")
include("engine.mod.loader")
include("engine.mod.manager")
include("loader")
include("linker")
include("linker.analysis")
