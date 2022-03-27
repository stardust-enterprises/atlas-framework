group = Coordinates.GROUP
version = Coordinates.VERSION

subprojects {
    group = "${parent?.group ?: Coordinates.GROUP}.${parent?.name ?: Coordinates.NAME}"
    version = Coordinates.VERSION

    // Maven Repositories
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
