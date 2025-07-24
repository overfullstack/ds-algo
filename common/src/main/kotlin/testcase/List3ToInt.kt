package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class List3ToInt(@SerialName("testcases") val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(
    @SerialName("inputs") val inputs: List<Input>,
    @SerialName("name") val name: String,
    @SerialName("output") val output: List<Output>,
  ) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: List<Int>?,
      @SerialName("2") val x2: List<Int>?,
      @SerialName("3") val x3: List<Int>?,
    )

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Triple<IntArray, IntArray, IntArray>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<List3ToInt>(readFileToString(it)).testcases }
      return testCases.map {
        Triple(
          it.inputs[0].x1!!.toIntArray(),
          it.inputs[1].x2!!.toIntArray(),
          it.inputs[2].x3!!.toIntArray(),
        ) to it.output[0].x1
      }
    }
  }
}
