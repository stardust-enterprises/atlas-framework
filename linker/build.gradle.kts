plugins {
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }
}

dependencies {
    implementation(project(":annotations"))
    implementation(project(":linker.analysis"))

    with(Dependencies) {
        implementation("org.jetbrains.kotlin", "kotlin-stdlib", KOTLIN)
    }
}
