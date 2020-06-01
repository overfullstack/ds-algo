package leetcode.backtracking.CombinationSum

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class CombinationSum2Test : StringSpec({
    "Combination Sum 2" {
        forAll(
            row(listOf(10, 1, 2, 7, 6, 1, 5), 8, setOf(setOf(1, 7), setOf(1, 2, 5), setOf(2, 6), setOf(1, 1, 6)))
        ) { arr, target, result ->
            combinationSum2(arr, target).map { it.toSet() }.toSet() shouldContainExactlyInAnyOrder result
        }
    }
})
