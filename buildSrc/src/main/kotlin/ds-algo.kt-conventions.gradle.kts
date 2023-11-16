import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { kotlin("jvm") }

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies { testImplementation(libs.kotestBundle) }

tasks {
  withType<KotlinCompile> {
    kotlinOptions.freeCompilerArgs =
      listOf("-opt-in=kotlin.RequiresOptIn", "-Xcontext-receivers", "-progressive")
  }
}
