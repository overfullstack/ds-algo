package testcase

import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: List<Int>)

    @Serializable data class Output(@SerialName("1") val x1: List<Int>)
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToReorderListArgs(
      vararg jsonFilePaths: String
    ): List<Pair<List<Int>, List<Int>>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<TestCase>(readFileInResourcesToString(it)).testcases
        }
      return testCases.map { it.inputs.flatMap { it.x1 } to it.output.flatMap { it.x1 } }
    }
  }
}
