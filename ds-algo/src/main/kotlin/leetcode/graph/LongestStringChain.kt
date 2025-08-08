package leetcode.graph

/* 07 Aug 2025 13:54 */

/** [1048. Longest String Chain](https://leetcode.com/problems/longest-string-chain) */
fun longestStrChainDP(words: Array<String>): Int {
  val dp = mutableMapOf<String, Int>()
  // ! sort by length
  return words
    .sortedBy { it.length }
    .maxOfOrNull { word ->
      (word.indices.maxOf { dp[word.removeRange(it..it)]?.plus(1) ?: 1 }).also { dp[word] = it }
    } ?: 0
}

fun longestStrChainDFS(words: Array<String>): Int {
  val map = mutableMapOf<String, Set<String>>()
  val wordsSet = words.toSet()
  for (word in words) {
    for (i in word.indices) {
      val derivedWord = word.removeRange(i..i)
      if (derivedWord in wordsSet) {
        map.merge(word, setOf(derivedWord), Set<String>::plus)
      }
    }
  }
  val cache = mutableMapOf<String, Int>()
  return map.keys.maxOfOrNull { 1 + dftPerChain(it, map, cache) } ?: 1
}

fun dftPerChain(
  word: String,
  map: MutableMap<String, Set<String>>,
  cache: MutableMap<String, Int>,
): Int {
  cache[word]?.let {
    return it
  }
  return (map[word]?.maxOfOrNull { 1 + dftPerChain(it, map, cache) } ?: 0).also { cache[word] = it }
}

fun main() {
  println(longestStrChainDFS(arrayOf("a", "b", "ba", "bca", "bda", "bdca"))) // 4
  println(longestStrChainDFS(arrayOf("xbc", "pcxbcf", "xb", "cxbc", "pcxbc"))) // 5
  println(longestStrChainDFS(arrayOf("abcd", "dbqca"))) // 1

  println(longestStrChainDP(arrayOf("a", "b", "ba", "bca", "bda", "bdca"))) // 4
  println(longestStrChainDP(arrayOf("xbc", "pcxbcf", "xb", "cxbc", "pcxbc"))) // 5
  println(longestStrChainDP(arrayOf("abcd", "dbqca"))) // 1
}
