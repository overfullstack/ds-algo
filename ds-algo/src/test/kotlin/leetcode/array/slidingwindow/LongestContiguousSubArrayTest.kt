package leetcode.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import leetcode.slidingwindow.findMaxLength

class LongestContiguousSubArrayTest :
  StringSpec({
    "Longest Contiguous Sub Array" {
      forAll(
        row(intArrayOf(0, 1), 2),
        row(intArrayOf(0, 1, 0), 2),
        row(intArrayOf(1, 0, 1, 1, 1, 0, 0), 6),
      ) { nums, result ->
        findMaxLength(nums) shouldBe result
      }
    }
  })
