dependencyResolutionManagement {
  versionCatalogs { create("libs") { from(files("libs.versions.toml")) } }
  pluginManagement {
    repositories {
      gradlePluginPortal()
      mavenCentral()
      maven("https://oss.sonatype.org/content/repositories/snapshots")
      google()
    }
  }
}

rootProject.name = "ds-algo-root"

include("common")

include("old")

include("ds-algo")
