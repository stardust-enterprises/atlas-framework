repositories {
    maven("https://libraries.minecraft.net")
}

dependencies {
    with(Dependencies) {
        compileOnly("net.minecraft", "launchwrapper", LAUNCHWRAPPER)
    }
}
