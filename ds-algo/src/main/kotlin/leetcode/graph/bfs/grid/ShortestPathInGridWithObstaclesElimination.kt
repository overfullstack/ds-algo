package leetcode.graph.bfs.grid

/* 30 Jul 2025 12:26 */

/**
 * [1293. Shortest Path in a Grid with Obstacles
 * Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)
 */
fun shortestPath(grid: Array<IntArray>, k: Int): Int { // * BFS
  val queue = ArrayDeque<Pair<Triple<Int, Int, Int>, Int>>()
  val visited = mutableSetOf<Triple<Int, Int, Int>>()
  val start = Triple(0, 0, k) to 0
  queue.add(start)
  // ! `visited` to avoid processing same cell with same `remainingK`
  visited += start.first
  while (queue.isNotEmpty()) {
    val (nextCell, distanceFromSource) = queue.removeFirst()
    val (row, col, remainingK) = nextCell
    if (row == grid.lastIndex && col == grid[0].lastIndex) {
      return distanceFromSource
    }
    val nextDistance = distanceFromSource + 1
    directions
      .asSequence()
      .map { it.first + row to it.second + col }
      .filter { isValid(it, grid) }
      .forEach { (nextRow, nextCol) ->
        val nextRemainingK =
          when {
            remainingK > 0 && grid[nextRow][nextCol] == 1 -> remainingK - 1
            grid[nextRow][nextCol] == 0 -> remainingK
            else -> null
          }
        nextRemainingK?.let {
          val next = Triple(nextRow, nextCol, it) to nextDistance
          if (next.first !in visited) {
            // ! Spl Visit-on-Enqueue as we have unweighted paths.
            // ! We won't have a scenario of having better paths later in the queue
            // ! It's more efficient as it avoids bloating `pq` with duplicate entries
            visited += next.first
            queue.add(next)
          }
        }
      }
  }
  return -1
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices

fun main() {
  println(
    shortestPath(
      arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(1, 1, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 1, 1),
        intArrayOf(0, 0, 0),
      ),
      1,
    )
  ) // 6
}
