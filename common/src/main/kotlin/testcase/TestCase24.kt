package testcase


import com.salesforce.revoman.input.readFileToString
import ds.TrieNode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class TestCase24(
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
      val x1: List<String>?,
      @SerialName("2")
      val x2: List<List<String>>?
    )

    @Serializable
    data class Output(
      @SerialName("1")
      val x1: List<String>
    )
  }

  companion object {
    fun parseJsonFileToTestCases(
      vararg jsonFilePaths: String
    ): List<Pair<List<Pair<String, String>>, List<String>>> {
      val json = Json { explicitNulls = false }
      val testCases =
        jsonFilePaths.flatMap { json.decodeFromString<TestCase24>(readFileToString(it)).testcases }
      return testCases.map {
        val operations = it.inputs[0].x1!!.drop(1)
        val args = it.inputs[1].x2!!.drop(1).flatMap { it.ifEmpty { listOf("null") } }
        operations.zip(args) to it.output[0].x1.drop(1)
      }
    }
  }
}
