package leetcode.sortandsearch

import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class MinInSortedRotatedKtTest : StringSpec() {

    init {
        "findMin" {
            forall(
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
