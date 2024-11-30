plugins {
  id("ds-algo.kt-conventions")
  id("ds-algo.sub-conventions")
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(project(":common"))
  implementation(libs.kotlinx.serialization)
  implementation(libs.bundles.kotlin.logging)
  implementation(libs.revoman)
}
