package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MaximumScoreNodeSequenceTest :
  StringSpec({
    "maximum score node sequence" {
      forAll(
        row(
          intArrayOf(5, 2, 9, 8, 4),
          arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(0, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
          ),
          24,
        ),
        row(
          intArrayOf(24, 15, 12, 5, 2, 8, 5, 16),
          arrayOf(
            intArrayOf(1, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 7),
            intArrayOf(7, 6),
            intArrayOf(6, 1),
            intArrayOf(1, 5),
            intArrayOf(7, 2),
            intArrayOf(2, 6),
            intArrayOf(1, 7),
            intArrayOf(7, 0),
            intArrayOf(0, 4),
            intArrayOf(1, 4),
            intArrayOf(3, 7),
          ),
          63,
        ),
      ) { nums, edges, expected ->
        maximumScore(nums, edges) shouldBe expected
      }
    }
  })
