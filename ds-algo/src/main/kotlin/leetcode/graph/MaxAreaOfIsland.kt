package leetcode.graph

fun maxAreaOfIsland(grid: Array<IntArray>): Int =
  grid.indices
    .asSequence()
    .flatMap { row -> grid[row].indices.asSequence().map { col -> row to col } }
    .filter { grid[it.first][it.second] == 1 }
    .map { dftPerGroup(grid, it) }
    .maxOrNull() ?: 0

private fun dftPerGroup(grid: Array<IntArray>, cell: Pair<Int, Int>): Int {
  grid[cell.first][cell.second] = 0 // ! marking visited
  return 1 + // * +1 for current node
    directions
      .asSequence()
      .map { (cell.first + it.first) to (cell.second + it.second) }
      .filter { isValid(it, grid) && grid[it.first][it.second] == 1 }
      .map { dftPerGroup(grid, it) }
      .sum()
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>): Boolean =
  cell.first in grid.indices && cell.second in grid[0].indices
