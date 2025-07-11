package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase17(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: Int)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(vararg jsonFilePaths: String): List<Pair<Int, Int>> {
      val testCases =
        jsonFilePaths.flatMap { Json.decodeFromString<TestCase17>(readFileToString(it)).testcases }
      return testCases.map { it.inputs.first().x1 to it.output.first().x1 }
    }
  }
}
