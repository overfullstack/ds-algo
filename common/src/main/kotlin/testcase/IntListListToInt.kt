package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class IntListListToInt(@SerialName("testcases") val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(
    @SerialName("inputs") val inputs: List<Input>,
    @SerialName("name") val name: String,
    @SerialName("output") val output: List<Output>,
  ) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: Int?,
      @SerialName("2") val x2: List<Int>?,
      @SerialName("3") val x3: List<Int>?,
    )

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Triple<Int, List<Int>, List<Int>>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<IntListListToInt>(readFileToString(it)).testcases
        }
      return testCases.map {
        Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.inputs[2].x3!!) to it.output[0].x1
      }
    }
  }
}
