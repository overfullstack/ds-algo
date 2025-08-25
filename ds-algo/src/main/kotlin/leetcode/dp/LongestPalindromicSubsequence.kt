/* gakshintala created on 9/23/19 */
package leetcode.dp

fun longestPalindromeSubseq(s: String): Int {
  val table = Array(s.length) { IntArray(s.length) }
  table.indices.forEach { table[it][it] = 1 } // having the same start char and end char

  // * table[i][j] indicates the longest palindromic subsequence of `s[i..j]`
  for (windowLen in 1 until s.length) {
    for ((wStart, wEnd) in (windowLen until s.length).withIndex()) {
      table[wStart][wEnd] =
        if (s[wStart] == s[wEnd]) {
          table[wStart + 1][wEnd - 1] + 2
        } else {
          // maxOf exclude char from left or right
          maxOf(table[wStart + 1][wEnd], table[wStart][wEnd - 1])
        }
    }
  }
  return table[0][s.lastIndex]
}

fun main() {
  val input = "bbbab"
  println("2D version: ${longestPalindromeSubseq(input)}")
}
