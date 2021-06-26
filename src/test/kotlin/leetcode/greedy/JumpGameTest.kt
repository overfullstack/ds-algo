package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class JumpGameTest : StringSpec({

  "canJump" {
    forAll(
      row(intArrayOf(2, 3, 1, 1, 4), true),
      row(intArrayOf(3, 2, 1, 0, 4), false),
      row(intArrayOf(0), true),
      row(intArrayOf(0, 1), false)
    ) { nums, result ->
      canJump(nums) shouldBe result
    }
  }
})
