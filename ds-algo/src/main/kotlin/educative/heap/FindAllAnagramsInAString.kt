package educative.heap

/* 29 Jul 2025 20:41 */

/**
 * [438. Find All Anagrams in a
 * String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)
 */
fun findAllAnagrams(a: String, b: String): List<Int> {
  if (a.length < b.length) return emptyList()
  val bFreq = b.groupingBy { it }.eachCount()
  val aWindowFreq = a.substring(0..b.lastIndex).groupingBy { it }.eachCount().toMutableMap()

  val result = mutableListOf<Int>()
  if (aWindowFreq == bFreq) {
    result.add(0)
  }
  for (end in b.lastIndex + 1..a.lastIndex) {
    val start = end - b.lastIndex
    aWindowFreq.computeIfPresent(a[start - 1]) { _, value -> if (value == 1) null else value.dec() }
    aWindowFreq.merge(a[end], 1, Int::plus)
    if (aWindowFreq == bFreq) {
      result.add(start)
    }
  }
  return result
}
