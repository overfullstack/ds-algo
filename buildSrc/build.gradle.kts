plugins { `kotlin-dsl` }

repositories {
  gradlePluginPortal()
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots")
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs.add("--enable-preview")
}

dependencies {
  implementation(libs.kotlin.gradle)
  implementation(libs.spotless.gradle)
  implementation(libs.kover.gradle)
  implementation(libs.testLogger.gradle)
}
