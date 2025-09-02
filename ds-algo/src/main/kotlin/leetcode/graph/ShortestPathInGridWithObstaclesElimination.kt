package leetcode.graph

import java.util.PriorityQueue

/* 30 Jul 2025 12:26 */

/**
 * [1293. Shortest Path in a Grid with Obstacles
 * Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)
 */
fun shortestPath(grid: Array<IntArray>, k: Int): Int {
  // ! Notice, descending by `remainingK`, favoring cells with more `remainingK`
  val pq = PriorityQueue(compareByDescending<Triple<Pair<Int, Int>, Int, Int>> { it.third })
  pq.add(Triple(0 to 0, 0, k))
  val minDistanceFromSource = Array(grid.size) { IntArray(grid[0].size) { Int.MAX_VALUE } }
  minDistanceFromSource[0][0] = 0
  while (pq.isNotEmpty()) {
    val (nextCell, distanceFromSource, remainingK) = pq.poll()
    val (row, col) = nextCell
    val nextDistance = distanceFromSource + 1
    directions
      .asSequence()
      .map { it.first + row to it.second + col }
      // ! You may revisit the same cell with less `remainingK` and shorter distance
      .filter { isValid(it, grid) && nextDistance < minDistanceFromSource[it.first][it.second] }
      .forEach { (nextRow, nextCol) ->
        when {
          remainingK > 0 && grid[nextRow][nextCol] == 1 -> {
            minDistanceFromSource[nextRow][nextCol] = nextDistance
            pq.add(Triple(nextRow to nextCol, nextDistance, remainingK - 1))
          }
          grid[nextRow][nextCol] == 0 -> {
            minDistanceFromSource[nextRow][nextCol] = nextDistance
            pq.add(Triple(nextRow to nextCol, nextDistance, remainingK))
          }
        }
      }
  }
  return if (minDistanceFromSource[grid.lastIndex][grid[0].lastIndex] == Int.MAX_VALUE) -1
  else minDistanceFromSource.last().last()
}

// ! ⏰TLE because we let each cell visit `k` times
// ! Unlike CheapestFlightsWithinKStops, we can't early terminate
// ! need to visit all cells to reach the destination
fun shortestPath2(grid: Array<IntArray>, k: Int): Int {
  val pq = PriorityQueue(compareBy<Triple<Pair<Int, Int>, Int, Int>> { it.second })
  val visited = mutableSetOf<Triple<Pair<Int, Int>, Int, Int>>()
  val start = Triple(0 to 0, 0, k)
  pq.add(start)
  visited += start
  while (pq.isNotEmpty()) {
    val (nextCell, distanceFromSource, remainingK) = pq.poll()
    val (row, col) = nextCell
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
          val next = Triple(nextRow to nextCol, nextDistance, it)
          if (next !in visited) {
            visited += next
            pq.add(next)
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
