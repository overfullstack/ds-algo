import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA

plugins {
  java
  `maven-publish`
  signing
  id("com.adarshr.test-logger")
}

repositories { mavenCentral() }

java {
  withSourcesJar()
  toolchain { languageVersion.set(JavaLanguageVersion.of(20)) }
}

tasks {
  testlogger.theme = MOCHA
  withType<Jar> { duplicatesStrategy = DuplicatesStrategy.EXCLUDE }
}
