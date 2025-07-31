package leetcode.graph

import java.util.PriorityQueue

/* 31 Jul 2025 14:28 */

fun swimInWater(grid: Array<IntArray>): Int {
  // ! Greedily choose the minimum next cell in the possible paths
  // ! Possible path can stem from any cell in the current path
  val pq = PriorityQueue(Comparator.comparingInt<Triple<Int, Int, Int>> { it.third })
  pq.add(Triple(0, 0, grid[0][0]))
  var maxInPath = Int.MIN_VALUE
  val visited = mutableSetOf<Pair<Int, Int>>()
  while (pq.isNotEmpty()) {
    val (row, col, elevation) = pq.poll()
    visited.add(row to col)
    maxInPath = maxOf(maxInPath, elevation)
    directions
      .map { it.first + row to it.second + col }
      .filter { isValid(it, grid) && it !in visited }
      .forEach { (row, col) ->
        val newMaxInPath = maxOf(maxInPath, grid[row][col])
        when {
          row == grid.lastIndex && col == grid[0].lastIndex -> return newMaxInPath
          else -> pq.add(Triple(row, col, newMaxInPath))
        }
      }
  }
  return 0
}

private val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)

private fun isValid(cell: Pair<Int, Int>, grid: Array<IntArray>) =
  cell.first in grid.indices && cell.second in grid[0].indices
