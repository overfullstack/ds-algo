/* gakshintala created on 12/26/19 */
package leetcode.dp

fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
  val rows = obstacleGrid.size
  val cols = obstacleGrid[0].size
  val table = Array(rows) { IntArray(cols) }
  table[0][0] = if (obstacleGrid[0][0] == 1) 0 else 1
  // This table[it - 1][0] is necessary, coz if the previous one is 0, current one should be 0 too.
  (1 until rows).forEach { table[it][0] = if (obstacleGrid[it][0] == 1) 0 else table[it - 1][0] }
  (1 until cols).forEach { table[0][it] = if (obstacleGrid[0][it] == 1) 0 else table[0][it - 1] }

  for (i in 1 until rows) {
    for (j in 1 until cols) {
      if (obstacleGrid[i][j] != 1) {
        table[i][j] = table[i - 1][j] + table[i][j - 1]
      }
    }
  }
  return table[rows - 1][cols - 1]
}

fun main() {
  print(
    uniquePathsWithObstacles(
      readln()
        .drop(2)
        .dropLast(2)
        .split("],[")
        .map { row -> row.split(",").map { it.trim().toInt() }.toIntArray() }
        .toTypedArray()
    )
  )
}
