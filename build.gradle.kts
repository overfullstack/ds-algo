plugins {
  id("ds-algo.root-conventions")
  id(libs.plugins.kover.get().pluginId)
}

kover { reports { total { html { onCheck = true } } } }
