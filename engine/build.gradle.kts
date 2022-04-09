plugins {
    `java-library`
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }
}

dependencies {
    api(project(":annotations"))
    api(project(":mod"))
    api(project(":mod.loader"))
    api(project(":mod.manager"))
}
