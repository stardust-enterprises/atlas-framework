plugins {
    `java-library`
}

repositories {
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly(project(":loader"))

    with(Dependencies) {
        compileOnly("net.minecraft", "launchwrapper", LAUNCHWRAPPER)
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
