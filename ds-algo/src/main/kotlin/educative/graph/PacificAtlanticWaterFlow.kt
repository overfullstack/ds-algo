package educative.graph

/* 23/7/25 16:25 */

fun estimateWaterFlow(grid: Array<IntArray>): Set<Pair<Int, Int>> {
  // Using mutable global visited as reach
  val pacificReach = mutableSetOf<Pair<Int, Int>>()
  val atlanticReach = mutableSetOf<Pair<Int, Int>>()

  // ! Both iterations include opposite edges from both oceans to check reachability
  // left and right
  for (row in grid.indices) {
    flowDFS(row, 0, grid, pacificReach)
    flowDFS(row, grid[0].lastIndex, grid, atlanticReach)
  }

  // top and bottom
  // ! Minor Optimization: Using `1` and `col-1` to skip top left and bottom right corner cells
  // which were already visited in the above loop
  for (col in 1..grid[0].lastIndex) {
    flowDFS(0, col, grid, pacificReach)
    flowDFS(grid.lastIndex, col - 1, grid, atlanticReach)
  }
  return pacificReach intersect atlanticReach
}

private fun flowDFS(
  curRow: Int,
  curCol: Int,
  grid: Array<IntArray>,
  visited: MutableSet<Pair<Int, Int>>,
) {
  when {
    (curRow to curCol) in visited -> return // ! As we are not filtering at the `flowDFS` call site
    else -> {
      visited += (curRow to curCol)
      directions
        .asSequence()
        .map { it.first + curRow to it.second + curCol }
        .filter { (row, col) ->
          isValid(row, col, grid[curRow][curCol], grid) && (row to col) !in visited
        }
        .forEach { flowDFS(it.first, it.second, grid, visited) }
    }
  }
}

private val directions = listOf((0 to -1), (0 to 1), (-1 to 0), (1 to 0))

private fun isValid(row: Int, col: Int, height: Int, grid: Array<IntArray>) =
  row in grid.indices && col in grid[0].indices && grid[row][col] >= height
