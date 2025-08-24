package leetcode.graph

import java.util.*

/* 30 Jul 2025 12:26 */

/**
 * [1293. Shortest Path in a Grid with Obstacles
 * Elimination](https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/)
 */
fun shortestPath(grid: Array<IntArray>, k: Int): Int {
  // ! Notice, descending by `remainingK`, favoring cells with more `remainingK`
  val pq = PriorityQueue(compareByDescending<Pair<Triple<Int, Int, Int>, Int>> { it.second })
  pq.add(Triple(0, 0, 0) to k)
  val minDistanceFromSource = Array(grid.size) { IntArray(grid[0].size) { Int.MAX_VALUE } }
  minDistanceFromSource[0][0] = 0
  while (pq.isNotEmpty()) {
    val nextCell = pq.poll()
    val (row, col, distanceFromSource) = nextCell.first
    val remainingK = nextCell.second
    // ! Calculating `nextDistance` here instead of in the loop, as it's always `+1`
    val nextDistance = distanceFromSource + 1
    directions
      .asSequence()
      .map { it.first + row to it.second + col }
      // * Takes care of making sure `nextDistance` is minimum
      // ! Greedy here trades-off `remainingK` over `distanceFromSource`.
      // ! This leads to reprocessing the same node later
      // ! when `nextDistance < minDistanceFromSource[it.first][it.second]`
      .filter { isValid(it, grid) && nextDistance < minDistanceFromSource[it.first][it.second] }
      // ! No visited, because we need same node with a `remainingK` to be visited multiple times
      // ! In this problem, the state is 3-dimensional: (row, col, remainingK)
      // Same physical cell `(row, col)`
      // can be visited multiple times with different k values
      // Each visit with different `remainingK` represents a legitimate different state
      .forEach { (nextRow, nextCol) ->
        when {
          remainingK > 0 && grid[nextRow][nextCol] == 1 -> {
            // ! This should be placed in the loop as we are comparing `remainingK`
            minDistanceFromSource[nextRow][nextCol] = nextDistance
            pq.add(Triple(nextRow, nextCol, nextDistance) to remainingK - 1)
          }
          grid[nextRow][nextCol] == 0 -> {
            minDistanceFromSource[nextRow][nextCol] = nextDistance
            pq.add(Triple(nextRow, nextCol, nextDistance) to remainingK)
          }
        }
      }
  }
  return if (minDistanceFromSource[grid.lastIndex][grid[0].lastIndex] == Int.MAX_VALUE) -1
  else minDistanceFromSource.last().last()
}

/** This TLEs as `pq` gets bloated, as all cells are equidistant, we end-up processing all cells */
fun shortestPath2(grid: Array<IntArray>, k: Int): Int {
  val minDistanceFromSource = Array(grid.size) { IntArray(grid[0].size) { Int.MAX_VALUE } }
  minDistanceFromSource[0][0] = 0
  val pq = PriorityQueue(compareBy<Pair<Triple<Int, Int, Int>, Int>> { it.first.third })
  pq.add(Triple(0, 0, 0) to k)
  while (pq.isNotEmpty()) {
    val nextCell = pq.poll()
    val (row, col, distanceFromSource) = nextCell.first
    val remainingK = nextCell.second
    if (row == grid.lastIndex && col == grid[0].lastIndex) {
      return distanceFromSource
    }
    minDistanceFromSource[row][col] = distanceFromSource
    // ! Calculating `nextDistance` here instead of in the loop, as it's always `+1`
    val nextDistance = distanceFromSource + 1
    directions
      .asSequence()
      .map { it.first + row to it.second + col }
      .filter { isValid(it, grid) && nextDistance < minDistanceFromSource[it.first][it.second] }
      .forEach { (nextRow, nextCol) ->
        when {
          remainingK > 0 && grid[nextRow][nextCol] == 1 ->
            pq.add(Triple(nextRow, nextCol, nextDistance) to remainingK - 1)
          grid[nextRow][nextCol] == 0 ->
            pq.add(Triple(nextRow, nextCol, nextDistance) to remainingK)
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
