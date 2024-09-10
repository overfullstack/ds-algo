plugins {
  id("ds-algo.kt-conventions")
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(libs.kotlinx.serialization)
  implementation(libs.bundles.kotlin.logging)
  implementation(libs.revoman)
}

println("***** common -->" + findProperty("kotest.framework.classpath.scanning.autoscan.disable"))
