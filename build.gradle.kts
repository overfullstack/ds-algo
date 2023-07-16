plugins {
  id("ds-algo.root-conventions")
  id("ds-algo.sub-conventions")
  id("ds-algo.kt-conventions")
}

dependencies {
  compileOnly(libs.jetbrains.annotations)
  implementation(libs.bundles.kotlin.logging)

  testImplementation(libs.assertj.core)
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) { useJUnitJupiter(libs.versions.junit.get()) }
  }
}

koverReport { defaults { xml { onCheck = true } } }
