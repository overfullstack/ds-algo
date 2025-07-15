package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase22(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: List<List<Int>>?,
      @SerialName("2") val x2: Int?,
      @SerialName("3") val x3: Int?,
    )

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Triple<List<List<Int>>, Int, Int>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase22>(readFileToString(it)).testcases }
      return testCases.map {
        Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.inputs[2].x3!!) to it.output[0].x1
      }
    }
  }
}
