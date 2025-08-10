package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class List_Str_IntToInt(@SerialName("testcases") val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(
    @SerialName("inputs") val inputs: List<Input>,
    @SerialName("name") val name: String,
    @SerialName("output") val output: List<Output>,
  ) {
    @Serializable
    data class Input(@SerialName("1") val x1: List<String>?, @SerialName("2") val x2: Int?)

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<List<String>, Int, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<List_Str_IntToInt>(readFileToString(it)).testcases
        }
      return testCases.map { Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.output[0].x1) }
    }
  }
}
