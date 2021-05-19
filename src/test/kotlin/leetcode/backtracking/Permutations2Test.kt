package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class Permutations2Test : StringSpec({
    "Permutations with duplicates" {
        forAll(
            row(intArrayOf(1, 1), listOf(listOf(1, 1))),
            row(intArrayOf(1, 2, 1), listOf(listOf(1, 1, 2), listOf(1, 2, 1), listOf(2, 1, 1))),
            row(intArrayOf(1, 1, 1, 1, 1), listOf(listOf(1, 1, 1, 1, 1))),
        ) { nums, result ->
            permuteUnique(nums) shouldContainExactlyInAnyOrder result
        }
    }
})
