package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class TestCase27(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(
      @SerialName("1") val x1: List<List<Int>>?,
      @SerialName("2") val x2: List<Int>?,
    )

    @Serializable data class Output(@SerialName("1") val x1: List<List<Int>>)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Pair<List<Pair<Int, Int>>, Pair<Int, Int>>, List<Pair<Int, Int>>>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase27>(readFileToString(it)).testcases }
      return testCases.map {
        (it.inputs[0].x1!!.map { it.toPair() } to it.inputs[1].x2!!.toPair()) to
          it.output[0].x1.map { it.toPair() }
      }
    }
  }
}
