package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase19(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: Int?,
      @SerialName("2") val x2: Int?,
      @SerialName("3") val x3: List<Int>?,
      @SerialName("4") val x4: List<Int>?,
    )

    @Serializable data class Output(@SerialName("1") val x1: Int)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Triple<Int, Int, List<Pair<Int, Int>>>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase19>(readFileToString(it)).testcases }
      return testCases.map {
        Triple(it.inputs[0].x1!!, it.inputs[1].x2!!, it.inputs[2].x3!!.zip(it.inputs[3].x4!!)) to
          it.output.first().x1
      }
    }
  }
}
