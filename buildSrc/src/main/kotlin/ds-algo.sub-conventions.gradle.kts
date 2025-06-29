import com.diffplug.spotless.LineEnding.PLATFORM_NATIVE

plugins {
  application
  id("org.jetbrains.kotlinx.kover")
  id("com.diffplug.spotless")
}

repositories { mavenCentral() }

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

java { 
  toolchain { languageVersion.set(JavaLanguageVersion.of(libs.jdk.toString())) } 
}

tasks.withType<JavaCompile>().configureEach {
  options.compilerArgs.add("--enable-preview")
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
    leadingSpacesToTabs(2)
    endWithNewline()
    targetExclude("build/**", ".gradle/**", "generated/**", "bin/**", "out/**", "tmp/**")
  }
  format("documentation") {
    target("*.md", "*.adoc")
    trimTrailingWhitespace()
    leadingSpacesToTabs(2)
    endWithNewline()
  }
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) { useJUnitJupiter(libs.junitVersion.toString()) }
  }
}
