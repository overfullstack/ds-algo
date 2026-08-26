plugins {
  id("ds-algo.kotlin-library-conventions")
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(project(":common"))
  implementation(libs.kotlinx.serialization)
}
