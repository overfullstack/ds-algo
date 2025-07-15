package educative.graph.unionfind

/* 05 Sep 2024 16:22 */

fun numberOfIslands(grid: Array<IntArray>): Int {
  val unionFind = UnionFind(grid)
  grid.indices
    .asSequence()
    .flatMap { row -> grid[row].indices.map { col -> row to col } }
    .filter { (row, col) -> grid[row][col] == 1 }
    .forEach { union(it, grid, unionFind) }
  return unionFind.countOf1s
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun union(cell: Pair<Int, Int>, grid: Array<IntArray>, unionFind: UnionFind) {
  grid[cell.first][cell.second] = 0
  directions
    .asSequence()
    .map { cell.first + it.first to cell.second + it.second }
    .filter { isValid(it, grid) }
    .forEach { unionFind.union(cell, it) }
}

private fun isValid(nextCell: Pair<Int, Int>, grid: Array<IntArray>): Boolean =
  nextCell.first in grid.indices &&
    nextCell.second in grid[0].indices &&
    grid[nextCell.first][nextCell.second] == 1

fun numberOfIslands2(grid: Array<IntArray>): Int {
  val unionFind = UnionFind(grid)
  val cols = grid[0].lastIndex
  for (row in grid.indices) {
    for (col in grid[0].indices) {
      if (grid[row][col] == 1) {
        grid[row][col] = 0
        val curIndex = row * cols + col
        if (row - 1 >= 0 && grid[row - 1][col] == 1) unionFind.union(curIndex, curIndex - cols)
        if (col - 1 >= 0 && grid[row][col - 1] == 1) unionFind.union(curIndex, curIndex - 1)
        if (row + 1 <= grid.lastIndex && grid[row + 1][col] == 1)
          unionFind.union(curIndex, curIndex + cols)
        if (col + 1 <= grid[0].lastIndex && grid[row][col + 1] == 1)
          unionFind.union(curIndex, curIndex + 1)
      }
    }
  }
  return unionFind.countOf1s
}

private class UnionFind(grid: Array<IntArray>) {
  val cols = grid[0].size
  var ranks = Array(grid.size * cols) { 1 }
  var roots = Array(grid.size * cols) { -1 }
  var countOf1s =
    grid.indices
      .asSequence()
      .flatMap { row -> grid[0].indices.map { col -> row to col } }
      .filter { grid[it.first][it.second] == 1 }
      .onEach { (row, col) ->
        val value = row * cols + col
        roots[value] = value
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
      countOf1s--
    }
  }
}
