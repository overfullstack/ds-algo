package algoexpert.ll.lrucache

import com.salesforce.revoman.input.readFileToString
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@Serializable
data class LRUCacheArgs(val classMethodsToCall: List<ClassMethodsToCall>, val maxSize: Int) {
  @Serializable data class ClassMethodsToCall(val arguments: List<JsonElement>, val method: String)

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToLRUCacheArgs(jsonFilePath: String): LRUCacheArgs =
      Json.decodeFromString<LRUCacheArgs>(readFileToString(jsonFilePath))
  }
}
