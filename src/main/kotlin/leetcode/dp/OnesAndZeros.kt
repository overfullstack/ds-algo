package leetcode.dp

/**
 * https://leetcode.com/problems/ones-and-zeroes/
 * Maximum number of strings that you can form with given m 0s and n 1s
 */
fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
  val table = Array(m + 1) { IntArray(n + 1) }
  for (str in strs) {
    val freq = str.groupingBy { it }.eachCount()
    val zeroesInStr = freq['0'] ?: 0
    val onesInStr = freq['1'] ?: 0
    for (zeroes in m downTo zeroesInStr) {
      for (ones in n downTo onesInStr) {
        table[zeroes][ones] =
          maxOf(table[zeroes][ones], 1 + table[zeroes - zeroesInStr][ones - onesInStr])
      }
    }
  }
  return table[m][n]
}
