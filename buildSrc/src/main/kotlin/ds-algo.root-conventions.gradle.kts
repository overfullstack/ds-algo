import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
import com.diffplug.spotless.LineEnding.PLATFORM_NATIVE
import com.diffplug.spotless.extra.wtp.EclipseWtpFormatterStep.XML

plugins {
  java
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

testlogger {
  theme = MOCHA_PARALLEL
  showCauses = false
  showSimpleNames = true
}
