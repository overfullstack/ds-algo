plugins {
  java
  id("org.jetbrains.kotlinx.kover")
  id("com.diffplug.spotless")
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

dependencies {
  kover(project(":common"))
  kover(project(":ds-algo"))
  kover(project(":old"))
}

spotlessPredeclare {
  fromProjectRepositories()
  java { googleJavaFormat() }
  kotlin { ktfmt() }
  kotlinGradle { ktfmt() }
}

kover { reports { total { html { onCheck = true } } } }
