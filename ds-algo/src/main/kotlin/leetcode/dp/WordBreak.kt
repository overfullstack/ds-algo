package leetcode.dp
/* gakshintala created on 10/2/19 */

/** [Word Break](https://leetcode.com/problems/word-break/) */
private fun wordBreak(word: String, wordDict: List<String>): Boolean {
  val wordDictSet = wordDict.toSet()
  // * One pass. For each index, compare with previously recorded end indices
  return word.indices
    .fold(listOf(-1)) { wordEndIndices, index ->
      when {
        wordEndIndices.any { prevWordEndIndex ->
          word.substring(prevWordEndIndex + 1..index) in wordDictSet
        } -> wordEndIndices + index
        else -> wordEndIndices
      }
    }
    .last() == word.lastIndex
}

fun wordBreakDP(s: String, wordDict: List<String>): Boolean {
  val dp = BooleanArray(s.length + 1)
  val dict = wordDict.toSet()
  dp[0] = true
  // * Building the word, one index at a time, expanding `endIndex` and checking if it can be broken
  // * -
  // * --
  // * ---
  for (endIndex in 1..(s.lastIndex + 1)) {
    for (startIndex in 0..endIndex) {
      // ! dp[i] indicates a substring that ends at `i - 1` present in `dict`
      // ! so `endIndex` is 1 ahead
      // * Check if the words until `startIndex` are present in `dict` and the word from
      // `startIndex` to `endIndex` is present in `dict`
      if (dp[startIndex] && dict.contains(s.substring(startIndex until endIndex))) {
        dp[endIndex] = true
        break
      }
    }
  }
  return dp.last()
}

private fun wordBreakDP2(s: String, wordDict: List<String>): Boolean {
  val table = Array(s.length) { BooleanArray(s.length) }
  val wordDictSet = wordDict.toSet()
  when {
    s.isEmpty() -> return false
    wordDict.contains(s) -> return true
  }
  (0..s.lastIndex).forEach { table[it][it] = wordDict.contains(s[it].toString()) }

  for (gap in 1..s.length) {
    for ((i, j) in (gap..s.lastIndex).withIndex()) {
      table[i][j] =
        when {
          wordDictSet.contains(s.substring(i..j)) -> true
          else ->
            (i until j).fold(false) { res, partition ->
              res || (table[i][partition] && table[partition + 1][j])
            }
        }
    }
  }
  return table[0][s.lastIndex]
}

fun main() {
  println(wordBreak("leetcode", listOf("leet", "code"))) // true
  println(wordBreakDP("leetcode", listOf("leet", "code"))) // true
  println(wordBreak("applepenapple", listOf("apple", "pen")))
  println(wordBreak("catsandog", listOf("cats", "dog", "sand", "and", "cat")))
  println(wordBreak("a", listOf("a")))
  println(wordBreak("ab", listOf("a", "b")))
}
