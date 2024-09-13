package testcase


import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase12(
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
      val x1: List<List<Int>>?,
      @SerialName("2")
      val x2: List<Int>?
    )

    @Serializable
    data class Output(
      @SerialName("1")
      val x1: Int
    )
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<Array<IntArray>, Set<Int>, Int>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<TestCase12>(readFileInResourcesToString(it)).testcases
        }
      return testCases.map { Triple(it.inputs[0].x1!!.map { it.toIntArray() }.toTypedArray(), it.inputs[1].x2!!.toSet(), it.output[0].x1)}
    }
  }
}
