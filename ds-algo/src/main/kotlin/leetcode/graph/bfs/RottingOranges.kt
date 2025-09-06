package leetcode.graph.bfs

/**
 * [Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) 0 -> empty cell 1 -> fresh 2 ->
 * rotten
 */
fun orangesRotting(grid: Array<IntArray>): Int { // * BFS
  if (grid.isEmpty()) {
    return 0
  }
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  var freshCount = 0

  for (row in grid.indices) {
    for (col in grid[0].indices) {
      when (grid[row][col]) {
        2 -> queue.add(Triple(row, col, 0)) // ! Start with time 0
        1 -> freshCount++
      }
    }
  }

  when {
    freshCount == 0 -> return 0
    queue.isEmpty() -> return -1
  }

  var maxTime = 0
  while (queue.isNotEmpty()) {
    val (row, col, time) = queue.removeFirst()
    maxTime = maxOf(maxTime, time)
    val newTime = time + 1
    freshCount -=
      directions
        .asSequence()
        .map { row + it.first to col + it.second }
        .filter { (newRow, newCol) -> isValid(newRow to newCol, grid) && grid[newRow][newCol] == 1 }
        .onEach { (newRow, newCol) ->
          grid[newRow][newCol] = 2 // ! mark rotten, also serves as visited
          queue.add(Triple(newRow, newCol, newTime))
        }
        .count()
  }

  return if (freshCount == 0) maxTime else -1
}

private val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices

fun orangesRottingLevelTracking(grid: Array<IntArray>): Int {
  if (grid.isEmpty()) {
    return 0
  }
  val queue = ArrayDeque<Pair<Int, Int>>()
  var freshCount = 0

  for (row in grid.indices) {
    for (col in grid[0].indices) {
      when (grid[row][col]) {
        2 -> queue.add(row to col)
        1 -> freshCount++
      }
    }
  }

  when {
    freshCount == 0 -> return 0
    queue.isEmpty() -> return -1
  }

  var count = 0
  while (queue.isNotEmpty()) {
    count++
    val size = queue.size
    repeat(size) {
      val rottenCell = queue.removeFirst()
      freshCount -=
        directions
          .asSequence()
          .map { (rottenCell.first + it.first) to (rottenCell.second + it.second) }
          .filter { isValid(it, grid) && grid[it.first][it.second] == 1 }
          .onEach {
            grid[it.first][it.second] = 2 // ! mark rotten, serves as visited
            queue.add(it.first to it.second)
          }
          .count()
    }
  }

  // ! count - 1 as you will hv an extra loop after all oranges are rotten.
  return if (freshCount == 0) count - 1 else -1
}
