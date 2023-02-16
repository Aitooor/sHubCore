plugins{
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version ("0.5.2")
}
group = "online.starsmc"
version = "1.0.0-SNAPSHOT"

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
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")
}