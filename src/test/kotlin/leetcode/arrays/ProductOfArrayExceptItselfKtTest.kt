package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ProductOfArrayExceptItselfKtTest : StringSpec() {

    init {
        "productExceptSelf" {
            forAll(
                row(intArrayOf(1, 2, 3, 4), intArrayOf(24, 12, 8, 6)),
                row(intArrayOf(1), intArrayOf(0))
            ) { nums, result ->
                productExceptSelf(nums) shouldBe result
            }
        }
    }

}
