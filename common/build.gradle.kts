plugins {
  id("ds-algo.kt-conventions")
  alias(libs.plugins.moshix)
}

dependencies {
  implementation(libs.moshi.kotlin)
  implementation(libs.bundles.kotlin.logging)
}

moshi {
  enableSealed by true
  generateProguardRules by false
}
