package educative.backtracking

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun floodFill(grid: Array<IntArray>, sr: Int, sc: Int, target: Int): Array<IntArray> {
  val validRc = directions
  .map { (r, c) -> sr + r to sc + c }
  .filter { (r, c) -> isValid(r, c, grid) && grid[sr][sc] != target && grid[sr][sc] == grid[r][c] }
  grid[sr][sc] = target
  var mutatedGrid = grid
  validRc.forEach { (r, c) ->
    mutatedGrid = floodFill(mutatedGrid, r, c, target)
  }
  return mutatedGrid
}

private fun isValid(r: Int, c: Int, grid: Array<IntArray>): Boolean =
  r in grid.indices && c in grid[0].indices
