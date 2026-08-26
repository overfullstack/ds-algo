package practice.graph.floodfill

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NumberOfClosedIslandsTest :
  StringSpec({
    "Number of closed islands" {
      forAll(
        // Canonical LeetCode example
        row(
          arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
            intArrayOf(1, 0, 0, 0, 0, 1, 1, 0),
            intArrayOf(1, 0, 1, 0, 1, 1, 1, 0),
            intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
          ),
          2,
        ),
        // Single row: every cell is a border, so no island can be closed
        row(arrayOf(intArrayOf(1, 0, 1, 0, 1)), 0),
        // Single column: same reasoning
        row(arrayOf(intArrayOf(1), intArrayOf(0), intArrayOf(1), intArrayOf(0)), 0),
        // Land group touches a border at ONE corner -> whole group disqualified
        row(arrayOf(intArrayOf(0, 0, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1)), 0),
        // Water "lake" inside a land ring -> still ONE closed island (hole doesn't split it)
        row(
          arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 1),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1),
          ),
          1,
        ),
        // Nested: a closed land ring with a separate closed island at its center -> 2
        row(
          arrayOf(
            intArrayOf(1, 1, 1, 1, 1, 1, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 0, 1, 1, 1, 0, 1),
            intArrayOf(1, 0, 1, 0, 1, 0, 1),
            intArrayOf(1, 0, 1, 1, 1, 0, 1),
            intArrayOf(1, 0, 0, 0, 0, 0, 1),
            intArrayOf(1, 1, 1, 1, 1, 1, 1),
          ),
          2,
        ),
        // Two separate interior dots
        row(
          arrayOf(
            intArrayOf(1, 1, 1, 1, 1),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(1, 1, 1, 1, 1),
          ),
          2,
        ),
        // Degenerate grids
        row(arrayOf(intArrayOf(0)), 0), // 1x1 land IS the border
        row(arrayOf(intArrayOf(1)), 0), // 1x1 water
        row(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0)), 0), // all land, all border
        row(arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 1, 1)), 0), // all water
      ) { grid, expected ->
        NumberOfClosedIslands().closedIsland(grid) shouldBe expected
      }
    }
  })
