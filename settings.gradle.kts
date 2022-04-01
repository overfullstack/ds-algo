pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal() // This is for other community plugins
  }
  val kotlinVersion: String by settings
  plugins {
    kotlin("jvm") version kotlinVersion // This is handy if there are multiple modules. This lets you declare version at one place.
  }
}

rootProject.name = "ds-algo"
