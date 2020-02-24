package leetcode.dp

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class MinimumPathSumKtTest : StringSpec() {

    init {
        "minPathSumRecursive" {
            minPathSumRecursive(arrayOf(intArrayOf(1,3,1), intArrayOf(1,5,1), intArrayOf(4,2,1))) shouldBe 7
        }

        "minPathSumDp" {
            minPathSumDp(arrayOf(intArrayOf(1,3,1), intArrayOf(1,5,1), intArrayOf(4,2,1))) shouldBe 7
        }
    }

}
