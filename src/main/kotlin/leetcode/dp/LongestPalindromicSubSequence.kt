/* gakshintala created on 9/23/19 */
package leetcode.dp

fun longestPalindromeSubseq(s: String): Int {
  val table = Array(s.length) { IntArray(s.length) }
  table.indices.forEach { table[it][it] = 1 } // having same start char and end char

  for (gap in 1 until s.length) {
    for ((i, j) in (gap until s.length).withIndex()) { // window iteration
      table[i][j] =
        if (s[i] == s[j]) {
          table[i + 1][j - 1] + 2
        } else {
          maxOf(table[i + 1][j], table[i][j - 1]) // maxOf exclude char from left or right
        }
    }
  }
  return table[0][s.lastIndex]
}

fun main() {
  print(longestPalindromeSubseq(readLine()!!))
}
