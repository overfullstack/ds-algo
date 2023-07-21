package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class JumpGame2Test :
  StringSpec({
    "Min Jumps to end" {
      forAll(
        row(intArrayOf(2, 3, 1, 1, 4), 2),
        row(intArrayOf(2, 3, 0, 1, 4, 2), 2),
        row(intArrayOf(0), 0),
        row(intArrayOf(1, 1, 1, 1, 1), 4),
        row(intArrayOf(1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1), 12)
      ) { nums, result ->
        minJumps(nums) shouldBe result
      }
    }
  })
