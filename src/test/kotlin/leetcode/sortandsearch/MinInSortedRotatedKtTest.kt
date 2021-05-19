package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinInSortedRotatedKtTest : StringSpec() {

    init {
        "findMin" {
            forAll(
                row(intArrayOf(3, 4, 5, 1, 2), 1),
                row(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0),
                row(intArrayOf(1), 1),
                row(intArrayOf(3, 1, 2), 1)
            ) { nums, result ->
                findMin(nums) shouldBe result
            }
        }
    }
}
