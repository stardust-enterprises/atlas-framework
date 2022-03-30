plugins {
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }
}

dependencies {
    implementation(project(":annotations"))
    implementation(project(":linker.analysis"))

    implementation(kotlin("stdlib"))
}
