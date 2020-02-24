package leetcode.greedy

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class MaxNumberFromArraysKtTest : StringSpec() {

    init {
        "maxNumber" {
            forall(
                row(intArrayOf(3, 4, 6, 5), intArrayOf(9, 1, 2, 5, 8, 3), 5, intArrayOf(9, 8, 6, 5, 3)),
                row(intArrayOf(6, 7), intArrayOf(6, 0, 4), 5, intArrayOf(6, 7, 6, 0, 4)),
                row(intArrayOf(3, 9), intArrayOf(8, 9), 3, intArrayOf(9, 8, 9)),
                row(intArrayOf(5, 6, 8), intArrayOf(6, 4, 0), 3, intArrayOf(8, 6, 4))
            ) { nums1, nums2, k, result ->
                maxNumber(nums1, nums2, k) shouldBe result
            }
        }
    }

}
