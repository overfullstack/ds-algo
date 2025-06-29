package testcase


import com.salesforce.revoman.input.readFileInResourcesToString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase14(
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
      val x2: Int?,
      @SerialName("3")
      val x3: Int?,
      @SerialName("4")
      val x4: Int?
    )

    @Serializable
    data class Output(
      @SerialName("1")
      val x1: List<List<Int>>
    )
  }

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Triple<List<List<Int>>, Triple<Int, Int, Int>, Array<IntArray>>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap {
          json.decodeFromString<TestCase14>(readFileInResourcesToString(it)).testcases
        }
      return testCases.map {
        Triple(
          it.inputs[0].x1!!,
          Triple(
            it.inputs[1].x2!!,
            it.inputs[2].x3!!,
            it.inputs[3].x4!!,
        ), it.output[0].x1.map { it.toIntArray() }.toTypedArray())
      }
    }
  }
}
