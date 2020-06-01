package backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class PermutationsTest : StringSpec({
    "Permutations" {
        permute(intArrayOf(1, 2, 3)) shouldContainExactlyInAnyOrder listOf(
            listOf(1, 2, 3),
            listOf(1, 3, 2),
            listOf(2, 1, 3),
            listOf(2, 3, 1),
            listOf(3, 1, 2),
            listOf(3, 2, 1)
        )
    }

    "Permutations with duplicates" {
        forAll(
            row(intArrayOf(1, 1), listOf(listOf(1, 1))),
            row(intArrayOf(1,2,1), listOf(listOf(1,1,2), listOf(1,2,1), listOf(2,1,1))),
            row(intArrayOf(1, 1, 1, 1, 1), listOf(listOf(1, 1, 1, 1, 1))),
        ) { nums, result ->
            permuteWithDups(nums) shouldContainExactlyInAnyOrder result
        }
    }
})
