package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class StrStrToBool(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: String? = null,
      @SerialName("2") val x2: String? = null,
    )

    @Serializable data class Output(@SerialName("1") val x1: Boolean)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<String, String, Boolean>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<StrStrToBool>(readFileToString(it)).testcases
        }
      return testCases.map { Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.output[0].x1) }
    }
  }
}
