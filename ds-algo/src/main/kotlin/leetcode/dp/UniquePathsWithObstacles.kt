/* gakshintala created on 12/26/19 */
package leetcode.dp

/** [63. Unique Paths II](https://leetcode.com/problems/unique-paths-ii/) */
fun uniquePathsWithObstacles1d(obstacleGrid: Array<IntArray>): Int {
  val dp = IntArray(obstacleGrid.first().size)
  dp[0] = 1
  for (row in obstacleGrid.indices) {
    for (col in obstacleGrid.first().indices) {
      // ! `table[row - 1][col]` is just borrowing from same col data from previous row.
      // ! Instead, we layer on the top of same 1D, one row at a time
      when {
        obstacleGrid[row][col] == 1 -> dp[col] = 0
        else -> dp[col] += dp[col - 1]
      }
    }
  }
  return dp.last()
}

fun uniquePathsWithObstacles2d(obstacleGrid: Array<IntArray>): Int {
  val rows = obstacleGrid.size
  val cols = obstacleGrid[0].size
  val table = Array(rows) { IntArray(cols) }
  table[0][0] = if (obstacleGrid[0][0] != 1) 1 else return 0
  // This table[it - 1][0] is necessary, coz if the previous one is 0, current one should be 0 too.
  for (row in 1 until rows) {
    table[row][0] = if (obstacleGrid[row][0] == 1) 0 else table[row - 1][0]
  }
  for (col in 1 until cols) {
    table[0][col] = if (obstacleGrid[0][col] == 1) 0 else table[0][col - 1]
  }

  for (row in 1 until rows) {
    for (col in 1 until cols) {
      if (obstacleGrid[row][col] != 1) {
        table[row][col] = table[row - 1][col] + table[row][col - 1]
      }
    }
  }
  return table[rows - 1][cols - 1]
}

fun uniquePathsWithObstacles2(obstacleGrid: Array<IntArray>): Int {
  val rows = obstacleGrid.size
  val cols = obstacleGrid[0].size
  val table = IntArray(rows)
  table[0] = if (obstacleGrid[0][0] != 1) 1 else return 0
  for (row in 1..rows) {
    for (col in 1..cols) {
      when {
        obstacleGrid[row][col] == 1 -> table[col] = 0 // We set to 0 for any obstacle found
        else -> table[col] += table[col - 1]
      }
    }
  }
  return table.last()
}

fun main() {
  print(
    uniquePathsWithObstacles1d(
      readln()
        .drop(2)
        .dropLast(2)
        .split("],[")
        .map { row -> row.split(",").map { it.trim().toInt() }.toIntArray() }
        .toTypedArray()
    )
  )
}
