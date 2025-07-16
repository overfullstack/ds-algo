package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase23(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable
    data class Input(@SerialName("1") val x1: List<String>?, @SerialName("2") val x2: String?)

    @Serializable data class Output(@SerialName("1") val x1: List<List<String>>)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<Pair<List<String>, String>, List<List<String>>>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase23>(readFileToString(it)).testcases }
      return testCases.map { (it.inputs[0].x1!! to it.inputs[1].x2!!) to it.output[0].x1 }
    }
  }
}
