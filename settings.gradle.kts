pluginManagement {
    repositories {
        gradlePluginPortal() // This is for other community plugins
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
    val kotlinEap: String by settings
    plugins {
        kotlin("jvm") version kotlinEap // This is handy if there are multiple modules. This lets you declare version at one place.
    }
}

rootProject.name = "ds-algo"
