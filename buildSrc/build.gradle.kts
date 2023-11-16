plugins { `kotlin-dsl` }

repositories {
  gradlePluginPortal()
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
  implementation(libs.kotlin.gradle)
  implementation(libs.spotless.gradle)
  implementation(libs.kover.gradle)
  implementation(libs.testLogger.gradle)
}
