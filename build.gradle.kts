group = Coordinates.GROUP
version = Coordinates.VERSION

subprojects {
    group = "${parent?.group ?: Coordinates.GROUP}.${parent?.name ?: Coordinates.NAME}"
    version = Coordinates.VERSION

    apply(plugin = "java-library")

    // Maven Repositories
    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks {
        named<JavaCompile>("compileJava") {
            targetCompatibility = "1.8"
            sourceCompatibility = "1.8"
        }
    }
}
