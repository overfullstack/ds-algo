package leetcode.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinSizeSubArrayWithSumGTKTest :
  StringSpec({
    "Min size sub array with sum greater than K" {
      forAll(row(intArrayOf(2, 3, 1, 2, 4, 3), 7, 2)) { nums, sum, result ->
        shortestSubArrayLenWithSumGTK(sum, nums) shouldBe result
      }
    }
  })
