package testcase

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer

@Serializable
data class TestCases(val testcases: List<Case>) {
  @Serializable
  data class Case(
    val name: String,
    val inputs: List<Map<String, JsonElement>>,
    val output: List<Map<String, JsonElement>>,
  ) {
    /** Decode the 1-based input [slot] (matching the JSON key) into the caller-supplied type. */
    inline fun <reified T> input(slot: Int): T = TestCases.decode(inputs[slot - 1].getValue("$slot"))

    /** Decode the 1-based output [slot] into the caller-supplied type. */
    inline fun <reified T> output(slot: Int): T = TestCases.decode(output[slot - 1].getValue("$slot"))
  }

  companion object {
    val json = Json {
      explicitNulls = false
      ignoreUnknownKeys = true
    }

    inline fun <reified T> decode(element: JsonElement): T = json.decodeFromJsonElement(serializer<T>(), element)

    /** Load and flatten testcases across one or more fixture paths. */
    fun load(vararg paths: String): List<Case> =
      paths.flatMap { json.decodeFromString<TestCases>(readFileToString(it)).testcases }
  }
}
