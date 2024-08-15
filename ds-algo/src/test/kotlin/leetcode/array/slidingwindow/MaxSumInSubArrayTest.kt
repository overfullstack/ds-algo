package leetcode.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MaxSumInSubArrayTest :
  StringSpec({
    "Max sum in subarray" {
      forAll(
        row(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6),
        row(intArrayOf(-1), -1),
        row(intArrayOf(-1, -2, -3), -1),
      ) { nums, result ->
        maxSumInSubArray(nums) shouldBe result
      }
    }
  })
