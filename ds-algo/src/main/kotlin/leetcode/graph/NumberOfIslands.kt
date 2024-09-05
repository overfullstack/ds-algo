package leetcode.graph

fun numIslands(grid: Array<CharArray>): Int =
  grid.indices
    .asSequence()
    .flatMap { row -> grid[row].indices.map { col -> row to col } }
    .filter { grid[it.first][it.second] == '1' }
    .onEach { grid.dft(it) }
    .count()

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun Array<CharArray>.dft(gridPoint: Pair<Int, Int>) {
  this[gridPoint.first][gridPoint.second] = '0' // mark visited
  directions
    .asSequence()
    .map { (gridPoint.first + it.first) to (gridPoint.second + it.second) }
    .filter { it.isValid(this) }
    .forEach { dft(it) }
}

private fun Pair<Int, Int>.isValid(grid: Array<CharArray>) =
  first in grid.indices && second in grid[0].indices && grid[first][second] == '1'
