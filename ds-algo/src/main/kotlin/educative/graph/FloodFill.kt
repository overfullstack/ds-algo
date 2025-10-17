package educative.graph

/** [733. Flood Fill](https://leetcode.com/problems/flood-fill/) */
fun floodFill(grid: Array<IntArray>, sr: Int, sc: Int, target: Int): Array<IntArray> {
  val validRc =
    directions
      .map { (r, c) -> sr + r to sc + c }
      .filter { (r, c) ->
        isValid(r, c, grid) && grid[sr][sc] != target && grid[sr][sc] == grid[r][c]
      }
  grid[sr][sc] = target
  validRc.forEach { (r, c) -> floodFill(grid, r, c, target) }
  return grid
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

private fun isValid(r: Int, c: Int, grid: Array<IntArray>): Boolean =
  r in grid.indices && c in grid[0].indices

fun main() {
  val grid = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 0), intArrayOf(1, 0, 1))
  println(floodFill(grid, 1, 1, 2).joinToString("\n") { it.joinToString() })
}
