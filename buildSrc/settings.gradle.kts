dependencyResolutionManagement {
  versionCatalogs { create("libs") { from(files("../libs.versions.toml")) } }

  pluginManagement {
    repositories {
      mavenCentral()
      gradlePluginPortal()
      maven("https://oss.sonatype.org/content/repositories/snapshots")
      google()
    }
  }
}
