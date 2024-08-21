package algoexpert.ll.lrucache

import com.salesforce.revoman.input.readFileInResourcesToString
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class LRUCacheArgs(val classMethodsToCall: List<ClassMethodsToCall>, val maxSize: Int) {
  @JsonClass(generateAdapter = true)
  data class ClassMethodsToCall(val arguments: List<Any>, val method: String)

  companion object {
    @OptIn(ExperimentalStdlibApi::class)
    fun parseJsonFileToLRUCacheArgs(jsonFilePath: String): LRUCacheArgs =
      Moshi.Builder()
        .build()
        .adapter<LRUCacheArgs>()
        .fromJson(readFileInResourcesToString(jsonFilePath))!!
  }
}
