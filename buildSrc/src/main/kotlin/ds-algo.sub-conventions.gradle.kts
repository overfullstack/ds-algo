plugins { java }

repositories { mavenCentral() }

java {
  withSourcesJar()
  toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}
