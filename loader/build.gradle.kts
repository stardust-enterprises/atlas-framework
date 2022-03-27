import java.nio.file.Files

plugins {
    `java-library`
}

val moduleName = "boot"

dependencies {
    with(Dependencies) {
        // will need shadowing
        arrayOf("core", "classic").forEach { module ->
            implementation("ch.qos.logback", "logback-$module", LOGBACK)
        }
        implementation("org.slf4j", "slf4j-api", SLF4J)

//        implementation("me.xtrm", "bootclassloader", BOOTCLASSLOADER)
//        implementation("fr.stardustenterprises", "deface", DEFACE)
//        implementation("fr.stardustenterprises", "retroloader", RETROLOADER)
    }
}

sourceSets {
    val main by sourceSets
    val test by sourceSets

    val api by creating {
        java.srcDir("src/api/java")
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
    from(sourceSets["api"])
}


tasks.processResources {
    doLast {
        val repoFile = project.buildDir.resolve(
            "resources" / "main" / "META-INF" /
                "$moduleName.repositories.atlas"
        )
        val depsFile = project.buildDir.resolve(
            "resources" / "main" / "META-INF" /
                "$moduleName.dependencies.atlas"
        )
        repoFile.parentFile.mkdirs()

        val repositories = project.repositories
            .filterIsInstance<MavenArtifactRepository>()
            .filter { it.url.scheme.startsWith("http") }
            .map { it.url.toString() }
        Files.write(repoFile.toPath(), repositories)

        val implementation by project.configurations
        val dependencies = implementation.allDependencies.map {
            it.group + ":" + it.name + ":" + it.version
        }
        Files.write(depsFile.toPath(), dependencies)
    }
}

operator fun String.div(str: String) =
    this + File.separatorChar + str
