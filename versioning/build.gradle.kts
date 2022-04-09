plugins {
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }
}

dependencies {
    with(Dependencies) {
        implementation("org.jetbrains.kotlin", "kotlin-stdlib", KOTLIN)
        testImplementation("org.jetbrains.kotlin", "kotlin-test", KOTLIN)
    }
}

tasks.test {
    useJUnitPlatform()
}
