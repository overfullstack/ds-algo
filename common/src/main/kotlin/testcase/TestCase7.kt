package testcase

import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase7(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(@SerialName("1") val x1: String? = null, @SerialName("2") val x2: Int? = null)

    @Serializable data class Output(@SerialName("1") val x1: List<String>)
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<String, Int, List<String>>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<TestCase7>(readFileInResourcesToString(it)).testcases
        }
      return testCases.map { Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.output[0].x1) }
    }
  }
}
