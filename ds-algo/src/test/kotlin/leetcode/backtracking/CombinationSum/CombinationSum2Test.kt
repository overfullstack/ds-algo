package leetcode.backtracking.CombinationSum

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll

class CombinationSum2Test :
  StringSpec({
    "Combination Sum 2" {
      forAll(
        row(
          listOf(10, 1, 2, 7, 6, 1, 5),
          8,
          listOf(listOf(1, 7), listOf(1, 2, 5), listOf(2, 6), listOf(1, 1, 6)),
        ),
        row(listOf(2, 5, 2, 1, 2), 5, listOf(listOf(1, 2, 2), listOf(5))),
      ) { arr, target, result ->
        TODO()
      }
    }
  })
