package leetcode.arrays.subarray

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

@ExperimentalStdlibApi
class ShortestSubArrayWithSumAtLeastKTest : StringSpec({

    "shortest Subarray With Sum AtLeast K" {
        forAll(
            row(intArrayOf(1), 1, 1),
            row(intArrayOf(1,2), 4, -1),
            row(intArrayOf(2,-1,2), 3, 3),
        ) { nums, k, result ->
            shortestSubarrayWithSumAtLeastK(nums, k) shouldBe result
        }
    }
})
