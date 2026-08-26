import com.diffplug.spotless.LineEnding.PLATFORM_NATIVE
import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
import org.gradle.api.tasks.testing.Test

plugins {
  `java-library`
  id("org.jetbrains.kotlinx.kover")
  id("com.diffplug.spotless")
  id("com.adarshr.test-logger")
}

repositories { mavenCentral() }

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

java { 
  toolchain { languageVersion.set(JavaLanguageVersion.of(libs.jdk.toString())) } 
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs.add("--enable-preview")
}

tasks.withType<JavaExec>().configureEach {
  jvmArgs("--enable-preview")
}

tasks.withType<Test>().configureEach { jvmArgs("--enable-preview") }

testlogger {
  theme = MOCHA_PARALLEL
  showCauses = false
  showSimpleNames = true
}

spotless {
  lineEndings = PLATFORM_NATIVE
  kotlin {
    target("src/*/kotlin/**/*.kt", "src/*/java/**/*.kt")
    targetExclude("build/**", ".gradle/**", "generated/**", "**/bin/**", "out/**", "tmp/**")
    ktfmt().googleStyle()
    trimTrailingWhitespace()
    endWithNewline()
  }
  kotlinGradle {
    target("*.gradle.kts", "src/**/*.gradle.kts")
    targetExclude("build/**", ".gradle/**", "generated/**", "**/bin/**", "out/**", "tmp/**")
    ktfmt().googleStyle()
    trimTrailingWhitespace()
    endWithNewline()
  }
  java {
    target("src/*/java/**/*.java")
    targetExclude("build/**", ".gradle/**", "generated/**", "**/bin/**", "out/**", "tmp/**")
    googleJavaFormat()
    importOrder()
    removeUnusedImports()
    forbidWildcardImports()
    trimTrailingWhitespace()
    leadingTabsToSpaces(2)
    endWithNewline()
  }
  format("documentation") {
    target("*.md", "*.adoc")
    trimTrailingWhitespace()
    leadingTabsToSpaces()
    endWithNewline()
  }
}

testing {
  suites {
    getByName<JvmTestSuite>("test") { useJUnitJupiter(libs.junitVersion.toString()) }
  }
}
