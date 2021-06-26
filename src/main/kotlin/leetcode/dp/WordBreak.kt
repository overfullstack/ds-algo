/*
https://leetcode.com/problems/word-break/

gakshintala created on 10/2/19
*/
package leetcode.dp

private fun wordBreak(word: String, wordDict: List<String>): Boolean {
  val wordDictSet = wordDict.toSet()
  return word.indices.fold(listOf(-1)) { wordEndIndices, index -> // Check with all end indices, including -1.
    if (wordEndIndices.any { prevWordEndIndex -> word.substring(prevWordEndIndex + 1..index) in wordDictSet }) {
      wordEndIndices + index
    } else {
      wordEndIndices
    }
  }.last() == word.lastIndex
}

private fun wordBreakDp(s: String, wordDict: List<String>): Boolean {
  val table = Array(s.length) { BooleanArray(s.length) }
  val wordDictSet = wordDict.toSet()
  when {
    s.isEmpty() -> return false
    wordDict.contains(s) -> return true
  }
  (0..s.lastIndex).forEach { table[it][it] = wordDict.contains(s[it].toString()) }

  for (gap in 1..s.length) {
    for ((i, j) in (gap..s.lastIndex).withIndex()) {
      table[i][j] = if (wordDictSet.contains(s.substring(i..j)))
        true
      else
        (i until j).fold(false) { res, partition ->
          res || (table[i][partition] && table[partition + 1][j])
        }
    }
  }
  return table[0][s.lastIndex]
}

fun main() {
  println(wordBreak("leetcode", listOf("leet", "code")))
  println(wordBreak("applepenapple", listOf("apple", "pen")))
  println(
    wordBreak(
      "catsandog",
      listOf("cats", "dog", "sand", "and", "cat")
    )
  )
  println(wordBreak("a", listOf("a")))
  println(wordBreak("ab", listOf("a", "b")))
}
