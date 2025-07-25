/* gakshintala created on 10/2/19 */
package leetcode.backtracking

/** https://leetcode.com/problems/word-break-ii/ */
fun wordBreakSentences(s: String, wordDict: List<String>): List<String> {
  return wordBreakSentencesInternal(s, wordDict.toSet()).map { it.joinToString(" ") }
}

private fun wordBreakSentencesInternal(
  str: String,
  dict: Set<String>,
  startIndex: Int = 0,
  cache: MutableMap<Int, List<List<String>>> = mutableMapOf(),
): List<List<String>> {
  // * The cache is being built from the end, so the next time we reach here,
  // * we shall have all broken combinations starting with this index
  cache[startIndex]?.let {
    return it
  }
  val sentences =
    (startIndex..str.lastIndex)
      .asSequence()
      .map { index -> index to str.substring(startIndex..index) }
      // filter for all the points which can be broken as word for this recursion.
      .filter { (_, word) -> word in dict }
      .flatMap { (index, wordInDict) ->
        when (index) {
          str.lastIndex -> listOf(listOf(wordInDict))
          else ->
            wordBreakSentencesInternal(str, dict, index + 1, cache).map { sentence ->
              listOf(wordInDict) + sentence
            }
        }
      }
      .toList()
  // If the word can't be broken, the last recursion would return an empty list, which shall be
  // passed-on, so the end results in an empty list
  return cache.merge(startIndex, sentences, Collection<List<String>>::plus)!!
}

fun main() {
  println(wordBreakSentences(readln(), readln().split(",").map { it.trim() }))
}
