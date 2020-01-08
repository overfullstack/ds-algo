package leetcode.arrays

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class ProductOfArrayExceptItselfKtTest : StringSpec() {

    init {
        "productExceptSelf" {
            forall(
                row(intArrayOf(1,2,3,4), intArrayOf(24,12,8,6)),
                row(intArrayOf(1), intArrayOf(0))
            ) {nums, result ->
                productExceptSelf(nums) shouldBe result
            }
        }
    }

}