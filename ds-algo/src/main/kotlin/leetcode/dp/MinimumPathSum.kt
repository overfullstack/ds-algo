/* gakshintala created on 12/29/19 */
package leetcode.dp

/** [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) */
fun minPathSumBottomUp(grid: Array<IntArray>): Int {
  if (grid.isEmpty()) {
    return 0
  }
  val table = Array(grid.size) { IntArray(grid[0].size) }
  table[0][0] = grid[0][0]
  val rows = table.lastIndex
  for (i in 1..rows) {
    table[i][0] = table[i - 1][0] + grid[i][0]
  }
  val cols = table[0].lastIndex
  for (i in 1..cols) {
    table[0][i] = table[0][i - 1] + grid[0][i]
  }

  for (i in 1..rows) {
    for (j in 1..cols) {
      table[i][j] = grid[i][j] + minOf(table[i - 1][j], table[i][j - 1])
    }
  }
  return table[rows][cols]
}

fun minPathSumTopDown(
  grid: Array<IntArray>,
  row: Int = grid.lastIndex,
  col: Int = grid[0].lastIndex,
  memo: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(),
): Int {
  memo[row to col]?.let {
    return it
  }
  val result =
    when {
      row < 0 || col < 0 -> Int.MAX_VALUE
      row == 0 && col == 0 -> grid[0][0]
      else ->
        grid[row][col] +
          minOf(minPathSumTopDown(grid, row - 1, col), minPathSumTopDown(grid, row, col - 1))
    }
  return memo.merge(row to col, result, ::maxOf)!!
}
