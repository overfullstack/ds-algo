import com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("com.adarshr.test-logger") version "3.2.0"
  application
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.github.microutils:kotlin-logging:2.1.23")
  runtimeOnly("org.apache.logging.log4j:log4j-core:2.17.2")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")

  // Junit
  testImplementation(platform("org.junit:junit-bom:5.8.2"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  // Kotest
  val kotestVersion = "5.3.0"
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
  testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
  }
  test {
    useJUnitPlatform()
    ignoreFailures = true
  }
}

testlogger {
  theme = MOCHA_PARALLEL
}
