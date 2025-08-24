package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import leetcode.graph.bfs.getFood

/* 30 Jul 2025 12:07 */

class ShortestPathToGetFoodTest :
  StringSpec({
    "Shortest PathTo Get Food" {
      forAll(
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', '*', 'O', 'O', 'O', 'X'),
            charArrayOf('X', 'O', 'O', '#', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X', 'X'),
          ),
          3,
        ),
        // Food adjacent to start - should return 1
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X'),
            charArrayOf('X', '*', '#'),
            charArrayOf('X', 'X', 'X'),
          ),
          1,
        ),
        // No path to food - blocked by walls
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X'),
            charArrayOf('X', '*', 'X', '#'),
            charArrayOf('X', 'X', 'X', 'X'),
          ),
          -1,
        ),
        // Longer path - need to go around obstacles
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', '*', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'O', 'X'),
            charArrayOf('X', '#', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X'),
          ),
          6,
        ),
        // Multiple possible paths - should find shortest
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', '*', 'O', '#', 'X'),
            charArrayOf('X', 'O', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X'),
          ),
          2,
        ),
        // Large maze with winding path
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', '*', 'O', 'X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'O', 'X', '#', 'O', 'X'),
            charArrayOf('X', 'X', 'O', 'O', 'O', 'O', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X', 'X', 'X'),
          ),
          6,
        ),
        // Start surrounded by walls except one opening
        row(
          arrayOf(
            charArrayOf('X', 'X', 'X', 'X', 'X'),
            charArrayOf('X', 'X', 'O', 'X', 'X'),
            charArrayOf('X', 'O', '*', 'O', '#'),
            charArrayOf('X', 'X', 'O', 'X', 'X'),
            charArrayOf('X', 'X', 'X', 'X', 'X'),
          ),
          2,
        ),
        // Food at corner, start at opposite corner
        row(
          arrayOf(
            charArrayOf('#', 'O', 'O'),
            charArrayOf('O', 'O', 'O'),
            charArrayOf('O', 'O', '*'),
          ),
          4,
        ),
      ) { grid, result ->
        getFood(grid) shouldBe result
      }
    }
  })
