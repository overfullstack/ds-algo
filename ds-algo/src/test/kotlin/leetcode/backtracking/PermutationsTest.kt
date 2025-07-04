package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class PermutationsTest :
  StringSpec({
    "Permutations" {
      intArrayOf(1, 2, 3).permute() shouldContainExactlyInAnyOrder
        listOf(
          listOf(1, 2, 3),
          listOf(1, 3, 2),
          listOf(2, 1, 3),
          listOf(2, 3, 1),
          listOf(3, 1, 2),
          listOf(3, 2, 1),
        )
    }
  })
