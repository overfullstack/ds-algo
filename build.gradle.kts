plugins {
    kotlin("jvm") version "1.3.71"
    idea
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    maven(url = "http://dl.bintray.com/kotlin/kotlin-eap")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("script-runtime"))

    testImplementation("org.junit.jupiter:junit-jupiter:+")
    //testImplementation("org.amshove.kluent:kluent:+")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:+") // for kotest framework
    testImplementation("io.kotest:kotest-assertions-core-jvm:+") // for kotest core jvm assertions
    testImplementation("org.slf4j:slf4j-simple:+")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_13.toString()
    }
    compileJava {
        sourceCompatibility = JavaVersion.VERSION_13.toString()
        targetCompatibility = JavaVersion.VERSION_13.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_13.toString()
    }
}
