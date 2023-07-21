import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
  id(libs.plugins.kover.pluginId)
  id(libs.plugins.detekt.pluginId) apply false
}

allprojects { apply(plugin = "ds-algo.root-conventions") }

koverReport { defaults { html { onCheck = true } } }

val detektReportMerge by
  tasks.registering(ReportMergeTask::class) {
    output.set(rootProject.buildDir.resolve("reports/detekt/merge.xml"))
  }

subprojects {
  apply(plugin = "ds-algo.sub-conventions")
  tasks.withType<Detekt>().configureEach {
    reports {
      xml.required = true
      html.required = true
    }
  }
  plugins.withType<DetektPlugin> {
    tasks.withType<Detekt> detekt@{
      finalizedBy(detektReportMerge)
      detektReportMerge.configure { input.from(this@detekt.xmlReportFile) }
    }
  }
}
