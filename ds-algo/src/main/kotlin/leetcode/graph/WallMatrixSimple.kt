package leetcode.graph

/**
 * Wall Matrix - Count lakes in an island (Simplified Version)
 *
 * Similar to LeetCode 1254 - Number of Closed Islands Given a matrix where 1 represents land and 0
 * represents water, count the number of water regions completely surrounded by land.
 */
fun countLakes(matrix: Array<IntArray>): Int {
  // Make a copy to avoid mutating the original
  val grid = matrix.map { it.clone() }.toTypedArray()

  // Step 1: Flood-fill all water touching the boundary
  // These water regions cannot be lakes as they reach the edge
  for (row in grid.indices) {
    floodFillWater(grid, row, 0) // Left edge
    floodFillWater(grid, row, grid[0].lastIndex) // Right edge
  }

  for (col in grid[0].indices) {
    floodFillWater(grid, 0, col) // Top edge
    floodFillWater(grid, grid.lastIndex, col) // Bottom edge
  }

  // Step 2: Count remaining water regions (these are the lakes)
  // Any water still marked as 0 must be completely surrounded
  return grid.indices
    .asSequence()
    .flatMap { row -> grid[0].indices.map { col -> row to col } }
    .filter { (row, col) -> grid[row][col] == 0 }
    .onEach { (row, col) -> floodFillWater(grid, row, col) } // Mark visited
    .count()
}

// Flood-fill water cells (0) and mark them as land (1)
private fun floodFillWater(grid: Array<IntArray>, row: Int, col: Int) {
  // Base cases: out of bounds or not water
  when {
    row !in grid.indices -> return
    col !in grid[0].indices -> return
    grid[row][col] != 0 -> return // Not water
  }

  // Mark as visited (convert water to land)
  grid[row][col] = 1

  // Recursively fill all 4 directions
  directions.forEach { (dr, dc) -> floodFillWater(grid, row + dr, col + dc) }
}

// Direction vectors for 4-connected neighbors
private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun main() {
  val grid1 =
    arrayOf(
      intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
      intArrayOf(1, 0, 0, 0, 0, 1, 1, 0),
      intArrayOf(1, 0, 1, 0, 1, 1, 1, 0),
      intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
      intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
    )
  println(countLakes(grid1)) // Expected: 2
}
