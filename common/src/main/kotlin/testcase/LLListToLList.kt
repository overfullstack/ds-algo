package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import utils.toPair

@Serializable
data class LLListToLList(val testcases: List<Testcase>) {
  @Serializable
  data class Testcase(val inputs: List<Input>, val name: String, val output: List<Output>) {
    @Serializable data class Input(@SerialName("1") val x1: List<List<List<Int>>>)

    @Serializable data class Output(@SerialName("1") val x1: List<List<Int>>)
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<List<List<Pair<Int, Int>>>, List<Pair<Int, Int>>>> {
      val testCases =
        jsonFilePaths.flatMap {
          Json.decodeFromString<LLListToLList>(readFileToString(it)).testcases
        }
      return testCases.map {
        it.inputs[0].x1.map { it.map { it.toPair() } } to it.output[0].x1.map { it.toPair() }
      }
    }
  }
}
