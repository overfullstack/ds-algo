/* gakshintala created on 12/26/19 */
package leetcode.dp

fun uniquePaths1d(m: Int, n: Int): Int {
  val table = IntArray(n) { 1 }
  repeat(m) {
    for (col in 1 until n) {
      table[col] += table[col - 1]
    }
  }
  return table[n - 1]
}

fun uniquePaths2d(m: Int, n: Int): Int {
  val table = Array(m) { IntArray(n) }

  (0 until m).forEach { table[it][0] = 1 }
  (0 until n).forEach { table[0][it] = 1 }

  for (row in 1 until m) {
    for (col in 1 until n) {
      table[row][col] = table[row - 1][col] + table[row][col - 1]
    }
  }
  return table[m - 1][n - 1]
}

fun main() {
  print(uniquePaths1d(readln().toInt(), readln().toInt()))
}
