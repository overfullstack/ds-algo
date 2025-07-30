package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/* 30 Jul 2025 19:12 */

class MinCostToReachDestinationInTimeTest :
  StringSpec({
    "min Cost to reach destination in time" {
      forAll(
        row(
          30,
          arrayOf(
            intArrayOf(0, 1, 10),
            intArrayOf(1, 2, 10),
            intArrayOf(2, 5, 10),
            intArrayOf(0, 3, 1),
            intArrayOf(3, 4, 10),
            intArrayOf(4, 5, 15),
          ),
          intArrayOf(5, 1, 2, 20, 20, 3),
          11,
        ),
        row(
          10,
          arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(0, 2, 1),
            intArrayOf(0, 3, 10),
            intArrayOf(1, 3, 2),
            intArrayOf(3, 2, 2),
            intArrayOf(4, 3, 1),
          ),
          intArrayOf(1, 1, 3, 2, 1),
          5,
        ),
      ) { maxTime, edges, passingFees, result ->
        minCost(maxTime, edges, passingFees) shouldBe result
      }
    }
  })
