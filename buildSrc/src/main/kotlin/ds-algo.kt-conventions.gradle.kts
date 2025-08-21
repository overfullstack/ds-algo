@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
  kotlin("jvm")
  kotlin("plugin.power-assert")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies { testImplementation(libs.kotestBundle) }

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xcontext-parameters", "-progressive", "-Xmulti-dollar-interpolation") } }

powerAssert {
  functions = listOf("io.kotest.matchers.shouldBe")
}
