plugins {
  id("ds-algo.kotlin-library-conventions")
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(libs.kotlinx.serialization)
  implementation(libs.revoman)
}
