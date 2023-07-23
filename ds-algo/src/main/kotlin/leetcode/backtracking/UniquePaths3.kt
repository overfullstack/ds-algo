/* gakshintala created on 12/26/19 */
package leetcode.backtracking

fun uniquePathsIII(grid: Array<IntArray>): Int {
  val rows = grid.size
  val cols = grid[0].size
  lateinit var startPoint: Pair<Int, Int>
  var zeroCount = 0
  for (i in 0 until rows) {
    for (j in 0 until cols) {
      when (grid[i][j]) {
        1 -> startPoint = i to j
        0 -> zeroCount++
      }
    }
  }
  return uniquePaths(grid, zeroCount, startPoint)
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun uniquePaths(grid: Array<IntArray>, zeroCount: Int, gridPoint: Pair<Int, Int>): Int {
  if (!gridPoint.isValid(grid)) {
    return 0
  }
  if (grid[gridPoint.first][gridPoint.second] == 2) {
    return if (zeroCount == -1) 1 else 0
  }
  grid[gridPoint.first][gridPoint.second] = -1
  val pathCount =
    directions.fold(0) { pathCount, direction ->
      val nextGridPoint =
        ((gridPoint.first + direction.first) to (gridPoint.second + direction.second))
      pathCount + uniquePaths(grid, zeroCount - 1, nextGridPoint)
    }
  grid[gridPoint.first][gridPoint.second] = 0
  return pathCount
}

private fun Pair<Int, Int>.isValid(grid: Array<IntArray>) =
  first >= 0 &&
    second >= 0 &&
    first < grid.size &&
    second < grid[0].size &&
    grid[first][second] != -1

fun main() {
  print(
    uniquePathsIII(
      readln()
        .drop(2)
        .dropLast(2)
        .split("],[")
        .map { row -> row.split(",").map { it.trim().toInt() }.toIntArray() }
        .toTypedArray()
    )
  )
}
