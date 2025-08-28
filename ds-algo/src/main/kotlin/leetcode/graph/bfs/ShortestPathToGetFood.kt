package leetcode.graph.bfs

/* 30 Jul 2025 11:27 */

/**
 * [1730 - Shortest Path to Get
 * Food](https://leetcode.ca/2021-03-13-1730-Shortest-Path-to-Get-Food/)
 */
fun getFood(grid: Array<CharArray>): Int {
  val myLocation =
    grid.withIndex().firstNotNullOf { (rowIndex, row) ->
      row.withIndex().find { it.value == '*' }?.let { rowIndex to it.index }
    }
  // ! Using normal queue instead of pq, as we don't have variable weights
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  queue.add(Triple(myLocation.first, myLocation.second, 0))

  while (queue.isNotEmpty()) {
    val (row, col, distance) = queue.removeFirst()
    if (grid[row][col] == '#') {
      return distance
    }
    directions
      .asSequence()
      .map { row + it.first to col + it.second }
      .filter { (nextRow, nextCol) ->
        isValid(nextRow to nextCol, grid) && grid[nextRow][nextCol] != 'X'
      }
      .forEach { (nextRow, nextCol) ->
        grid[nextRow][nextCol] = 'X' // ! Mark visited
        queue.add(Triple(nextRow, nextCol, distance + 1)) // ! Distance added to queue itself
      }
  }
  return -1
}

fun getFoodWithLevelTracking(grid: Array<CharArray>): Int {
  val myLocation =
    grid.withIndex().firstNotNullOf { (rowIndex, row) ->
      row.withIndex().find { it.value == '*' }?.let { rowIndex to it.index }
    }
  val queue = ArrayDeque<Pair<Int, Int>>()
  queue.add(myLocation)
  var distance = 0
  while (queue.isNotEmpty()) {
    distance++ // ! Constant weight, so BFS is optimal and Dijkstra is overkill
    repeat(queue.size) { // ! Needed for level tracking, like `distance`
      val cell = queue.removeFirst()
      if (grid[cell.first][cell.second] == '#') {
        return distance
      }
      directions
        .asSequence()
        .map { cell.first + it.first to cell.second + it.second }
        .filter { isValid(it, grid) && grid[it.first][it.second] != 'X' }
        .forEach {
          grid[it.first][it.second] = 'X' // ! Mark visited
          queue.add(it)
        }
    }
  }
  return -1
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun isValid(cell: Pair<Int, Int>, grid: Array<CharArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices
