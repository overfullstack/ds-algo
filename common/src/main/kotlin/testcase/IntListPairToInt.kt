package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class IntListPairToInt(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(@SerialName("1") val x1: Int?, @SerialName("2") val x2: List<List<Int>>?)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Pair<Int, List<Pair<Int, Int>>>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<IntListPairToInt>(readFileToString(it)).testcases
        }
      return testCases.map {
        (it.inputs[0].x1!! to it.inputs[1].x2!!.map { it.toPair() }) to it.output[0].x1
      }
    }
  }
}
