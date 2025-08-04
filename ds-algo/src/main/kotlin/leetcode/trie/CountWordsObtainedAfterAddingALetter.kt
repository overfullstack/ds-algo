package leetcode.trie

import ds.TrieNode

/* 04 Aug 2025 16:11 */

/**
 * [2135. Count Words Obtained After Adding a
 * Letter](https://leetcode.com/problems/count-words-obtained-after-adding-a-letter)
 */
fun wordCount(startWords: Array<String>, targetWords: Array<String>): Int {
  val trie = TrieNode()
  startWords.map { it.toList().sorted().joinToString("") }.forEach { trie.insert(it) }
  return targetWords.count { word ->
    val sortedWord = word.toList().sorted().joinToString("")
    sortedWord.indices.map { sortedWord.removeRange(it..it) }.any { trie.isPresent(it) }
  }
}

fun main() {
  println(wordCount(arrayOf("ant", "act", "tack"), arrayOf("tack", "act", "acti"))) // 2
  println(wordCount(arrayOf("ab", "a"), arrayOf("abc", "abcd"))) // 1
}
