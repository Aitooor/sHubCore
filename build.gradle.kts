plugins{
    id("java")
    id("com.github.johnrengelman.shadow") version ("7.1.2")
    id("net.minecrell.plugin-yml.bukkit") version ("0.5.2")
}
group = "online.starsmc"
version = "no-shaded"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

bukkit {
    main = "$group.hubcore.Main"
    name = rootProject.name
    description = "HubCore pluging for 1.8 to 1.19"
    apiVersion = "1.13"
    version = rootProject.version.toString()
    softDepend = listOf("PlaceholderAPI")
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")

    implementation("team.unnamed:inject:1.0.1")
    implementation("org.slf4j:slf4j-api:2.0.6")
    implementation("me.fixeddev:commandflow-bukkit:0.5.2")
}

tasks {
    shadowJar {
        archiveVersion.set("1.0.0")
        archiveClassifier.set("")
    }
}
tasks {
    build {
        dependsOn("shadowJar")
    }
}