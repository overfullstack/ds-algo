package leetcode.arrays.slidingwindow.string

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/ Find all the start indices of p's
 * anagrams in s.
 */
fun findAnagrams(s: String, p: String): List<Int> {
  val pFreqMap = p.groupingBy { it }.eachCount().toMutableMap()
  var start = 0
  var matchCountInWindow = 0
  val result = mutableListOf<Int>()
  for ((i, char) in s.withIndex()) {
    pFreqMap.computeIfPresent(char) { _, freq ->
      if (freq > 0) matchCountInWindow++
      freq.dec()
    }
    if (matchCountInWindow == p.length) {
      result.add(start)
    }
    val windowSize = i - start + 1
    // Slide the window by a char and undo freq operation
    if (windowSize == p.length) {
      pFreqMap.computeIfPresent(s[start]) { _, freq ->
        if (freq >= 0) matchCountInWindow--
        freq.inc()
      }
      start++
    }
  }
  return result
}

fun main() {
  println(findAnagrams("cbaebabacd", "abc"))
}
