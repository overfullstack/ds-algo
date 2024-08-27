package testcase

import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase4(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: String)

    @Serializable data class Output(@SerialName("1") val x1: String)
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(vararg jsonFilePaths: String): List<Pair<String, String>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<TestCase4>(readFileInResourcesToString(it)).testcases
        }
      return testCases.map { it.inputs[0].x1 to it.output[0].x1 }
    }
  }
}
