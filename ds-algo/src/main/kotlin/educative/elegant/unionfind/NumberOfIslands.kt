package educative.elegant.unionfind

/* 05 Sep 2024 16:22 */

/** [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) */
fun numberOfIslands(grid: Array<IntArray>): Int {
  val unionFind = UnionFind(grid)
  grid.indices
    .asSequence()
    .flatMap { row -> grid[row].indices.map { col -> row to col } }
    .filter { (row, col) -> grid[row][col] == 1 }
    .forEach { groupAdjacent1s(it, grid, unionFind) }
  return unionFind.countOf1s
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun groupAdjacent1s(cell: Pair<Int, Int>, grid: Array<IntArray>, unionFind: UnionFind) {
  grid[cell.first][cell.second] = 0
  directions
    .asSequence()
    .map { cell.first + it.first to cell.second + it.second }
    .filter { isValid(it, grid) && grid[it.first][it.second] == 1 }
    .forEach { unionFind.union(cell, it) }
}

private fun isValid(nextCell: Pair<Int, Int>, grid: Array<IntArray>): Boolean =
  nextCell.first in grid.indices && nextCell.second in grid[0].indices

private class UnionFind(grid: Array<IntArray>) {
  val cols = grid[0].size
  // ! `-1` to indicate no connection for `0`
  var roots = IntArray(grid.size * cols) { -1 }
  var ranks = IntArray(grid.size * cols)
  var countOf1s =
    grid.indices
      .asSequence()
      .flatMap { row -> grid[0].indices.map { col -> row to col } }
      .filter { grid[it.first][it.second] == 1 }
      .onEach { (row, col) ->
        val value = row * cols + col // ! converting row-col to 1D
        roots[value] = value // Self root
      }
      .count()

  tailrec fun find(value: Int): Int =
    when {
      roots[value] == value -> value
      else -> find(roots[value])
    }

  fun union(n1: Pair<Int, Int>, n2: Pair<Int, Int>) =
    union(n1.first * cols + n1.second, n2.first * cols + n2.second)

  fun union(n1: Int, n2: Int) {
    val root1 = find(n1)
    val root2 = find(n2)
    if (root1 != root2) {
      when {
        ranks[root1] < ranks[root2] -> roots[root1] = root2
        ranks[root1] > ranks[root2] -> roots[root2] = root1
        else -> {
          roots[root1] = root2
          ranks[root2]++
        }
      }
      countOf1s-- // ! Negate `group-1` from the countOf1s
    }
  }
}
