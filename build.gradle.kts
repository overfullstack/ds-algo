import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm")
  id("com.adarshr.test-logger") version "3.1.0"
  application
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.github.microutils:kotlin-logging:2.0.11")
  runtimeOnly("org.apache.logging.log4j:log4j-core:2.14.1")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")

  // Junit
  testImplementation(platform("org.junit:junit-bom:5.8.1"))
  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

  // Kotest
  val kotestVersion = "4.6.1"
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks {
  compileJava {
    options.encoding = "UTF-8"
  }
  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_16.toString()
      freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
  }
  test {
    useJUnitPlatform()
    ignoreFailures = true
  }
}

testlogger {
  setTheme("mocha")
}
