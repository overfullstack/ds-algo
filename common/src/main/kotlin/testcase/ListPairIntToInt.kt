package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class ListPairIntToInt(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(@SerialName("1") val x1: List<List<Int>>?, @SerialName("2") val x2: Int?)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<List<Pair<Int, Int>>, Int, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<ListPairIntToInt>(readFileToString(it)).testcases
        }
      return testCases.map {
        Triple(it.inputs.first().x1!!.map { it.toPair() }, it.inputs[1].x2!!, it.output.first().x1)
      }
    }
  }
}
