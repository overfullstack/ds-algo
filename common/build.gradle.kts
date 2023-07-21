plugins {
  id("ds-algo.kt-conventions")
  alias(libs.plugins.moshix)
}

dependencies {
  implementation(libs.moshi.kotlin)
}

moshi {
  enableSealed by true
  generateProguardRules by false
}
