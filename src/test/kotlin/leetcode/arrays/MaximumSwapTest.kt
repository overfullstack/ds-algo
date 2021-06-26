package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MaximumSwapTest : StringSpec({
  "maximumSwap1" {
    maximumSwap(2736) shouldBe 7236
  }

  "maximumSwap2" {
    maximumSwap(9973) shouldBe 9973
  }

  "maximumSwap3" {
    maximumSwap(10) shouldBe 10
  }
})
