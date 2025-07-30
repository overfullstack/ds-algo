package leetcode.graph

import java.util.*

/* 30 Jul 2025 12:26 */

fun shortestPath(grid: Array<IntArray>, k: Int): Int {
  // ! Notice, descending by `k`
  val queue = PriorityQueue(compareByDescending<Pair<Triple<Int, Int, Int>, Int>> { it.second })
  queue.add(Triple(0, 0, 0) to k)
  val minPath = Array(grid.size) { IntArray(grid[0].size) { Int.MAX_VALUE } }
  minPath[0][0] = 0
  while (queue.isNotEmpty()) {
    val nextCell = queue.poll()
    val (row, col, distance) = nextCell.first
    val curK = nextCell.second
    val nextDistance = distance + 1
    directions
      .asSequence()
      .map { it.first + row to it.second + col }
      .filter { isValid(it, grid) }
      // ! No visited, because we need same node with a `curK` to be visited multiple times
      .forEach { (row, col) ->
        if (nextDistance < minPath[row][col]) {
          when {
            curK > 0 && grid[row][col] == 1 -> {
              minPath[row][col] = nextDistance
              queue.add(Triple(row, col, nextDistance) to curK - 1)
            }
            grid[row][col] == 0 -> {
              minPath[row][col] = nextDistance
              queue.add(Triple(row, col, nextDistance) to curK)
            }
          }
        }
      }
  }
  return if (minPath[grid.lastIndex][grid[0].lastIndex] == Int.MAX_VALUE) -1
  else minPath[grid.lastIndex][grid[0].lastIndex]
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices
