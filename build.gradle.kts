import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.adarshr.test-logger") version "2.1.0"
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
    application
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

/*configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
}*/

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://dl.bintray.com/kotlin/kotlin-dev")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("script-runtime"))

    implementation("io.github.microutils:kotlin-logging:+")
    runtimeOnly("org.apache.logging.log4j:log4j-core:+")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:+")

    testImplementation("org.junit.jupiter:junit-jupiter-api:+")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:+")

    listOf("runner-junit5", "assertions-core", "runner-console", "property").forEach {
        testImplementation("io.kotest:kotest-$it-jvm:latest.integration")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(arrayOf("--enable-preview"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_14.toString()
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=enable")
    }
}

tasks.withType<Test> {
    ignoreFailures = true
    useJUnitPlatform {
        excludeEngines("junit-vintage")
    }
    jvmArgs("--enable-preview")
}

testlogger {
    setTheme("mocha")
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = true
    showCauses = true
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
