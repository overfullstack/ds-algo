plugins {
  // Keep pinned Spotless on the consumer classpath for ktfmt; `apply false` does not apply it to root.
  // https://github.com/diffplug/spotless/issues/2646
  alias(libs.plugins.spotless) apply false
  id("ds-algo.root-conventions")
}
