plugins {
  // Keep Spotless on the consumer classpath for ktfmt: https://github.com/diffplug/spotless/issues/2646
  alias(libs.plugins.spotless) apply false
  id("ds-algo.root-conventions")
}
