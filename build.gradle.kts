plugins {
    kotlin("jvm") version "1.3.61"
    idea
}

group = "com.gakshintala.ds-algo"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:+")
    //testImplementation("org.amshove.kluent:kluent:+")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:+")

    testCompile("org.slf4j:slf4j-simple:+")
    

    implementation(kotlin("stdlib-jdk8"))
}
    
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "12"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "12"
    }
}