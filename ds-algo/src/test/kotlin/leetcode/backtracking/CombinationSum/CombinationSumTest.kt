package leetcode.backtracking.CombinationSum

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class CombinationSumTest :
  StringSpec({
    "Combination Sum" {
      forAll(
        row(intArrayOf(2, 3, 6, 7), 7, listOf(listOf(7), listOf(2, 2, 3))),
        row(intArrayOf(2, 3, 5), 8, listOf(listOf(2, 2, 2, 2), listOf(2, 3, 3), listOf(3, 5))),
        row(
          intArrayOf(7, 3, 2),
          18,
          listOf(
            listOf(7, 7, 2, 2),
            listOf(7, 3, 3, 3, 2),
            listOf(7, 3, 2, 2, 2, 2),
            listOf(3, 3, 3, 3, 3, 3),
            listOf(3, 3, 3, 3, 2, 2, 2),
            listOf(3, 3, 2, 2, 2, 2, 2, 2),
            listOf(2, 2, 2, 2, 2, 2, 2, 2, 2),
          ),
        ),
      ) { arr, target, result ->
        combinationSumDP(arr, target) shouldContainExactlyInAnyOrder result
      }
    }
  })
