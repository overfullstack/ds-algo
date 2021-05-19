package leetcode.arrays.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LongestSubArraySumKTest : StringSpec({
    "Longest SubArray Sum K" {
        forAll(
            row(intArrayOf(1, -1, 5, -2, 3), 3, 4),
            row(intArrayOf(-2, -1, 2, 1), 1, 2),
            row(intArrayOf(10, 5, 2, 7, 1, 9), 15, 4),
            row(intArrayOf(-5, 8, -14, 2, 4, 12), -5, 5)
        ) { nums, k, result ->
            longestSubArraySumK(nums, k) shouldBe result
        }
    }
})
