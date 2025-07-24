package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ListIntToInt(@SerialName("testcases") val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(
    @SerialName("inputs") val inputs: List<Input>,
    @SerialName("name") val name: String,
    @SerialName("output") val output: List<Output>,
  ) {
    @Serializable
    data class Input(@SerialName("1") val x1: List<Int>?, @SerialName("2") val x2: Int?)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Pair<List<Int>, Int>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<ListIntToInt>(readFileToString(it)).testcases
        }
      return testCases.map { (it.inputs[0].x1!! to it.inputs[1].x2!!) to it.output[0].x1 }
    }
  }
}
