plugins {
  kotlin("jvm")
  id("com.adarshr.test-logger") version "3.0.0"
  id("io.gitlab.arturbosch.detekt") version "1.17.1"
  id("com.diffplug.spotless") version "5.14.0"
  application
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.github.microutils:kotlin-logging:+")
  runtimeOnly("org.apache.logging.log4j:log4j-core:+")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:+")

  // Junit
  testImplementation(platform("org.junit:junit-bom:+"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  // Kotest
  val kotestVersion = "+"
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_16

tasks {
  compileJava {
    options.compilerArgs.addAll(arrayOf("--enable-preview"))
    options.encoding = "UTF-8"
  }
  compileKotlin {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_16.toString()
    }
  }
  test {
    useJUnitPlatform()
    ignoreFailures = true
    jvmArgs("--enable-preview")
  }
}

testlogger {
  setTheme("mocha")
  showExceptions = true
  showStackTraces = false
  showFullStackTraces = false
  showCauses = false
  slowThreshold = 2000
  showSummary = true
  showSimpleNames = true
  showPassed = true
  showSkipped = true
  showFailed = true
  showStandardStreams = true
  showPassedStandardStreams = true
  showSkippedStandardStreams = true
  showFailedStandardStreams = true
}

spotless {
  kotlin {
    // by default the target is every '.kt' and '.kts` file in the java sourcesets
    ktlint("0.41.0").userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
  kotlinGradle {
    target("*.gradle.kts")
    ktlint("0.41.0").userData(mapOf("indent_size" to "2", "continuation_indent_size" to "2"))
  }
  java {
    importOrder()
    removeUnusedImports()
    googleJavaFormat("1.10.0")
    trimTrailingWhitespace()
    indentWithSpaces(2)
    endWithNewline()
  }
}
