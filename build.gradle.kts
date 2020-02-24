plugins {
    kotlin("jvm") version "1.3.61"
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
    testImplementation("io.kotlintest:kotlintest-runner-junit5:+")
    testImplementation("org.slf4j:slf4j-simple:+")
}

/*tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_13.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_13.toString()
    }
}*/
