plugins { kotlin("jvm") }

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies { testImplementation(libs.kotestBundle) }

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xcontext-parameters", "-progressive", "-Xmulti-dollar-interpolation") } }
