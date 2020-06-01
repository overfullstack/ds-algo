package leetcode.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MinimumPathSumTest : StringSpec({
    "minPathSumRecursive" {
        minPathSumRecursive(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))) shouldBe 7
    }

    "minPathSumDp" {
        minPathSumDp(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))) shouldBe 7
    }
})
