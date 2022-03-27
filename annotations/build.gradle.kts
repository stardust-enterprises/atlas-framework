plugins {
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }

    `java-library`
}

dependencies {
    with(Dependencies) {
        kotlinModules.forEach {
            implementation("org.jetbrains.kotlin", "kotlin-$it", KOTLIN)
        }
        testImplementation("org.jetbrains.kotlin", "kotlin-test", KOTLIN)
    }
}
