package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class TestCase13(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(var name: String, var inputs: List<Inputs>, var output: List<Output>) {
    @Serializable data class Inputs(@SerialName("1") var x1: List<List<Int>>)

    @Serializable data class Output(@SerialName("1") var x1: List<Int>)
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<List<Pair<Int, Int>>, Pair<Int, Int>>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase13>(readFileToString(it)).testcases }
      return testCases.map { it.inputs[0].x1.map { it.toPair() } to it.output[0].x1.toPair() }
    }
  }
}
