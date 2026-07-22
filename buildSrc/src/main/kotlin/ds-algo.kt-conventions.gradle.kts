@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  kotlin("jvm")
  kotlin("plugin.power-assert")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies { testImplementation(libs.kotestBundle) }

kotlin {
  jvmToolchain(libs.jdk.toString().toInt())
  compilerOptions {
    freeCompilerArgs.addAll("-jvm-default=enable", "-progressive", "-Xannotation-default-target=param-property", "-Xconsistent-data-class-copy-visibility")
  }
}

powerAssert {
  functions = listOf("io.kotest.matchers.shouldBe")
}

tasks.withType<KotlinJvmCompile>().configureEach {
  jvmTargetValidationMode = JvmTargetValidationMode.WARNING
}
