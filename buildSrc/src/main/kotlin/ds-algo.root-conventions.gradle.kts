import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
import com.diffplug.spotless.LineEnding.PLATFORM_NATIVE
import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep.XML

plugins {
  java
  id("com.diffplug.spotless")
  id("org.jetbrains.kotlinx.kover")
  id("com.adarshr.test-logger")
}

version = VERSION

group = GROUP_ID

description = "DS Algo"

repositories {
  mavenCentral()
  maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
  maven("https://oss.sonatype.org/content/repositories/snapshots")
  maven("https://repo.spring.io/milestone")
}

spotless {
  lineEndings = PLATFORM_NATIVE
  kotlin {
    ktfmt().googleStyle()
    target("src/*/kotlin/**/*.kt", "src/*/java/**/*.kt")
    trimTrailingWhitespace()
    endWithNewline()
    targetExclude("build/**", ".gradle/**", "generated/**", "bin/**", "out/**", "tmp/**")
  }
  kotlinGradle {
    ktfmt().googleStyle()
    trimTrailingWhitespace()
    endWithNewline()
    targetExclude("build/**", ".gradle/**", "generated/**", "bin/**", "out/**", "tmp/**")
  }
  java {
    toggleOffOn()
    target("src/*/java/**/*.java")
    importOrder()
    removeUnusedImports()
    googleJavaFormat()
    trimTrailingWhitespace()
    indentWithSpaces(2)
    endWithNewline()
    targetExclude("build/**", ".gradle/**", "generated/**", "bin/**", "out/**", "tmp/**")
  }
  format("documentation") {
    target("*.md", "*.adoc")
    trimTrailingWhitespace()
    indentWithSpaces(2)
    endWithNewline()
  }
}

testlogger {
  theme = MOCHA_PARALLEL
  showCauses = false
  showSimpleNames = true
}
