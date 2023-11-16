plugins {
  application
  id("org.jetbrains.kotlinx.kover")
}

repositories { mavenCentral() }

java {
  withSourcesJar()
  toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) { useJUnitJupiter("5.10.1") }
  }
}
