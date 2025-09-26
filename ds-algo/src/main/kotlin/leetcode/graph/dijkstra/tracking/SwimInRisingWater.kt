package leetcode.graph.dijkstra.tracking

import java.util.PriorityQueue

/* 31 Jul 2025 14:28 */

/** [778. Swim in Rising Water](https://leetcode.com/problems/swim-in-rising-water/) */
fun swimInWater(grid: Array<IntArray>): Int {
  // ! Greedily choose the path where max in that path is the minimum among all possible paths
  // ! Possible path can stem from any cell in the current path
  val pq = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.third })
  pq.add(Triple(0, 0, grid[0][0]))
  val visited = Array(grid.size) { BooleanArray(grid[0].size) }
  while (pq.isNotEmpty()) {
    val (row, col, maxInPath) = pq.poll()
    if (row == grid.lastIndex && col == grid[0].lastIndex) {
      return maxInPath // ! elevation in this problem represents time
    }
    visited[row][col] = true
    directions
      .map { it.first + row to it.second + col }
      .filter { isValid(it, grid) && !visited[it.first][it.second] }
      .forEach { (row, col) ->
        val newMaxInPath = maxOf(maxInPath, grid[row][col])
        pq.add(Triple(row, col, newMaxInPath))
      }
  }
  return 0
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices
