plugins {
  application
  id("org.jetbrains.kotlinx.kover")
}

repositories { mavenCentral() }

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

java { 
  toolchain { languageVersion.set(JavaLanguageVersion.of(libs.jdk.toString())) } 
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs.add("--enable-preview")
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) { useJUnitJupiter(libs.junitVersion.toString()) }
  }
}
