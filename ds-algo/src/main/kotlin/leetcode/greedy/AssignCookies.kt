package leetcode.greedy

/* 10 Aug 2025 16:33 */

/** [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/) */
fun findContentChildren(g: IntArray, s: IntArray): Int {
  val gSorted = g.sorted()
  val sSorted = s.sorted()
  var gi = 0
  var si = 0
  var count = 0
  while (gi <= gSorted.lastIndex && si <= sSorted.lastIndex) {
    if (gSorted[gi] <= sSorted[si]) {
      count++
      gi++
    }
    si++
  }
  return count
}

fun main() {
  println(findContentChildren(intArrayOf(1, 2, 3), intArrayOf(1, 1)))
  println(findContentChildren(intArrayOf(1, 2), intArrayOf(1, 2, 3)))
}
