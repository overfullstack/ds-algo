/* gakshintala created on 10/2/19 */
package leetcode.backtracking

/** https://leetcode.com/problems/word-break-ii/ */
fun wordBreakSentences(s: String, wordDict: List<String>): List<String> {
  return wordBreakSentences(s, wordDict.toSet()).map { it.joinToString(" ") }
}

private fun wordBreakSentences(
  str: String,
  dict: Set<String>,
  startIndex: Int = 0,
  cache: MutableMap<Int, List<List<String>>> = mutableMapOf(),
): List<List<String>> {
  cache[startIndex]?.let {
    return it
  }
  val sentences =
    (startIndex..str.lastIndex)
      .map { index -> index to str.substring(startIndex..index) }
      // filter for all the points which can be broken as word for this recursion.
      .filter { (_, word) -> word in dict }
      .flatMap { (index, wordInDict) ->
        when (index) {
          // If this is placed at the start of recursion, we need to update cache at two places.
          str.lastIndex -> listOf(listOf(wordInDict))
          else -> // Sentence append can't be made eager, as this result is directly put into cache.
            wordBreakSentences(str, dict, index + 1, cache).map { sentence ->
              listOf(wordInDict) + sentence
            }
        }
      }
  // If the word can't be broken, the last recursion would return empty list, which shall be
  // passed-on, so end results in empty list
  return cache.merge(startIndex, sentences, Collection<List<String>>::plus)!!
}

fun main() {
  println(wordBreakSentences(readln(), readln().split(",").map { it.trim() }))
}
