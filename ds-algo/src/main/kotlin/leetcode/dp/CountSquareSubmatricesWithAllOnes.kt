package leetcode.dp

/* 11 Aug 2025 12:33 */

/**
 * [1277. Count Square Submatrices with All
 * Ones](https://leetcode.com/problems/count-square-submatrices-with-all-ones/)
 */
fun countSquares(matrix: Array<IntArray>): Int {
  var count = 0
  for (row in matrix.indices) {
    for (col in matrix.first().indices) {
      matrix[row][col] +=
        if (row >= 1 && col >= 1 && matrix[row][col] == 1)
          minOf(matrix[row - 1][col], matrix[row - 1][col - 1], matrix[row][col - 1])
        else 0
      count += matrix[row][col]
    }
  }
  return count
}

fun main() {
  println(
    countSquares(arrayOf(intArrayOf(0, 1, 1, 1), intArrayOf(1, 1, 1, 1), intArrayOf(0, 1, 1, 1)))
  )
  println(countSquares(arrayOf(intArrayOf(1, 0, 1), intArrayOf(1, 1, 0), intArrayOf(1, 1, 0))))
}
