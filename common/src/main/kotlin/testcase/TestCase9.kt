package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase9(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: List<List<String>>)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(vararg jsonFilePaths: String): List<Pair<Array<IntArray>, Int>> {
      val testCases =
        jsonFilePaths.flatMap { Json.decodeFromString<TestCase9>(readFileToString(it)).testcases }
      return testCases.map {
        it.inputs[0].x1.map { it.map { it.toInt() }.toIntArray() }.toTypedArray() to it.output[0].x1
      }
    }
  }
}
