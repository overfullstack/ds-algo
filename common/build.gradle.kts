plugins {
  id("ds-algo.kt-conventions")
  alias(libs.plugins.moshix)
  alias(libs.plugins.ksp)
}

dependencies {
  implementation(libs.moshi.kotlin)
  implementation(libs.bundles.kotlin.logging)
}

moshi {
  enableSealed = true
}
