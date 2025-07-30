package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class ListPairToBoolean(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: List<List<Int>>)

    @Serializable data class Output(@SerialName("1") val x1: Boolean)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<List<Pair<Int, Int>>, Boolean>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<ListPairToBoolean>(readFileToString(it)).testcases
        }
      return testCases.map { it.inputs.first().x1.map { it.toPair() } to it.output.first().x1 }
    }
  }
}
