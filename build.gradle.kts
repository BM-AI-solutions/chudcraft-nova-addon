group = "org.jeffstein"
version = "1.0.0" // Aether-Tekkit Nova Addon

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.paperweight)
    alias(libs.plugins.nova)
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    implementation(libs.nova)
}

addon {
    name = "Aether-Tekkit"
    version = project.version.toString()
    main = "org.jeffstein.AetherTekkitAddon"
    description = "A magical and industrial addon combining Aether dimensions with Tekkit-inspired machinery"
    authors = listOf("jeffstein")

    // output directory for the generated addon jar is read from the "outDir" project property (-PoutDir="...")
    val outDir = project.findProperty("outDir")
    if (outDir is String)
        destination.set(File(outDir))
}
