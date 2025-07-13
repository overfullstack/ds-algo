package testcase


import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase20(
  val testcases: List<Testcase>
) {
  @Serializable
  data class Testcase(
    val inputs: List<Input>,
    val name: String,
    val output: List<Output>
  ) {
    @Serializable
    data class Input(
      @SerialName("1")
      val x1: List<Int>
    )

    @Serializable
    data class Output(
      @SerialName("1")
      val x1: Boolean
    )
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<IntArray, Boolean>> {
      val testCases =
        jsonFilePaths.flatMap { Json.decodeFromString<TestCase20>(readFileToString(it)).testcases }
      return testCases.map { it.inputs.first().x1.toIntArray() to it.output.first().x1 }
    }
  }
}
