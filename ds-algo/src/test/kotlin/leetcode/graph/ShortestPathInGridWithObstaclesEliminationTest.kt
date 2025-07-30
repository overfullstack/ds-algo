package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/* 30 Jul 2025 13:47 */

class ShortestPathInGridWithObstaclesEliminationTest :
  StringSpec({
    "shortest path in grid with obstacles elimination" {
      forAll(
        row(
          arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 1, 1, 1, 1, 1, 1, 1),
            intArrayOf(0, 1, 0, 1, 1, 1, 1, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 1, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
          ),
          1, 20
        ),
        row(
          arrayOf(
            intArrayOf(0, 0),
            intArrayOf(1, 0),
            intArrayOf(1, 0),
            intArrayOf(1, 0),
            intArrayOf(1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, 0),
            intArrayOf(0, 1),
            intArrayOf(0, 1),
            intArrayOf(0, 1),
            intArrayOf(0, 0),
            intArrayOf(1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, 0)
          ),
          4, 14
        ),
      ) { grid, k, result ->
        shortestPath(grid, k) shouldBe result
      }
    }
  })
