plugins {
    `java-library`
    with(Plugins) {
        kotlin("jvm") version KOTLIN
    }
}

dependencies {
    implementation(project(":versioning"))

    with(Dependencies) {
        implementation("org.jetbrains.kotlin", "kotlin-stdlib", KOTLIN)
    }
}

sourceSets {
    val main by sourceSets
    val test by sourceSets

    val api by creating {
        java.srcDir("src/api/kotlin")
        resources.srcDir("src/api/resources")

        this.compileClasspath += main.compileClasspath
        this.runtimeClasspath += main.runtimeClasspath
    }

    arrayOf(main, test).forEach {
        it.compileClasspath += api.output
        it.runtimeClasspath += api.output
    }
}

tasks.jar {
    from(sourceSets["api"].output)
}
