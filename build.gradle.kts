plugins { id(libs.plugins.kover.pluginId) }

allprojects { apply(plugin = "ds-algo.root-conventions") }

kover { reports { total { html { onCheck = true } } } }

subprojects { apply(plugin = "ds-algo.sub-conventions") }
