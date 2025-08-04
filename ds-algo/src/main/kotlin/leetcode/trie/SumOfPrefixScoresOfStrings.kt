package leetcode.trie

/* 04 Aug 2025 11:49 */

fun sumPrefixScores(words: Array<String>): IntArray {
  val trie = TrieNodeWithCount()
  for (word in words) {
    trie.insert(word)
  }

  return words.map { word -> trie.getPrefixScoreSum(word) }.toIntArray()
}

private class TrieNodeWithCount(value: Char = Char.MIN_VALUE) {
  var visitedCount = 1
  val children = arrayOfNulls<TrieNodeWithCount>(26)

  fun insert(word: String) {
    var crawl = this
    for (ch in word) {
      crawl =
        crawl.children[ch - 'a']?.also { it.visitedCount++ }
          ?: TrieNodeWithCount(ch).also { crawl.children[ch - 'a'] = it }
    }
  }

  fun getPrefixScoreSum(word: String): Int {
    var crawl: TrieNodeWithCount? = this
    return word.sumOf { ch ->
      crawl?.children[ch - 'a']?.visitedCount?.also {
        @Suppress("AssignedValueIsNeverRead")
        crawl = crawl?.children[ch - 'a']
      }
        ?: return 0
    }
  }
}

fun main() {
  val testCase1 = arrayOf("abc", "ab", "bc", "b")
  val expected1 = intArrayOf(5, 4, 3, 2)
  val result1 = sumPrefixScores(testCase1)
  println("Test Case 1: ${result1.contentEquals(expected1)}")

  val testCase2 = arrayOf("abcd")
  val expected2 = intArrayOf(4)
  val result2 = sumPrefixScores(testCase2)
  println("Test Case 2: ${result2.contentEquals(expected2)}")
}
