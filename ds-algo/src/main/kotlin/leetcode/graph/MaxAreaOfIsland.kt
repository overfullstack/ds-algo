package leetcode.graph

fun maxAreaOfIsland(grid: Array<IntArray>): Int =
  grid.indices
    .asSequence()
    .flatMap { row -> grid[row].indices.asSequence().map { col -> row to col } }
    .filter { grid[it.first][it.second] == 1 }
    .map { grid.dft(it) }
    .maxOrNull()
    ?: 0

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun Array<IntArray>.dft(nextGridPoint: Pair<Int, Int>): Int {
  this[nextGridPoint.first][nextGridPoint.second] = 0 // marking visited.
  return 1 +
    directions
      .asSequence() // * +1 for current node
      .map { (nextGridPoint.first + it.first) to (nextGridPoint.second + it.second) }
      .filter { it.isValid(this) }
      .map { dft(it) }
      .sum()
}

private fun Pair<Int, Int>.isValid(grid: Array<IntArray>): Boolean =
  first >= 0 &&
    first <= grid.lastIndex &&
    second >= 0 &&
    second <= grid[0].lastIndex &&
    grid[first][second] == 1
