package leetcode.graph

/* 30 Jul 2025 11:27 */

fun getFood(grid: Array<CharArray>): Int {
  val myLocation =
    grid.withIndex().firstNotNullOf { (rowIndex, row) ->
      row.withIndex().find { it.value == '*' }?.let { rowIndex to it.index }
    }
  val queue = ArrayDeque<Pair<Int, Int>>()
  queue.add(myLocation)
  var distance = 0
  while (queue.isNotEmpty()) {
    distance++
    repeat(queue.size) {
      val nextCell = queue.removeFirst()
      directions
        .asSequence()
        .map { nextCell.first + it.first to nextCell.second + it.second }
        .filter { isValid(it, grid) }
        .forEach {
          when {
            grid[it.first][it.second] == '#' -> return distance
            else -> {
              grid[it.first][it.second] = 'X'
              queue.add(it)
            }
          }
        }
    }
  }
  return -1
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

fun isValid(cell: Pair<Int, Int>, grid: Array<CharArray>) =
  cell.first in grid.indices &&
    cell.second in grid[0].indices &&
    grid[cell.first][cell.second] in setOf('O', '#')
