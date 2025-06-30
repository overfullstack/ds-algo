package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase3(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: String)

    @Serializable data class Output(@SerialName("1") val x1: Boolean)
  }

  companion object {
    fun parseJsonFileToTestCases(vararg jsonFilePaths: String): List<Pair<String, Boolean>> {
      val testCases =
        jsonFilePaths.flatMap { Json.decodeFromString<TestCase3>(readFileToString(it)).testcases }
      return testCases.map { it.inputs[0].x1 to it.output[0].x1 }
    }
  }
}
