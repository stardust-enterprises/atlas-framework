group = Coordinates.group
version = Coordinates.version

subprojects {
    group = "${parent?.group ?: Coordinates.group}.${parent?.name ?: Coordinates.name}"
    version = Coordinates.version

    // Maven Repositories
    repositories {
        mavenLocal()
        mavenCentral()
        Repositories.mavenUrls.forEach(::maven)
    }
}
