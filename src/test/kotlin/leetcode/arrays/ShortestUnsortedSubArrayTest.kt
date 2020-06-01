package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ShortestUnsortedSubArrayTest: StringSpec({
    "Shortest Unsorted SubArray" {
        forAll(
            row(intArrayOf(2, 6, 4, 8, 10, 9, 15), 5),
            row(intArrayOf(2,1), 2),
        ) { arr, result ->
            shortestUnsortedSubArray(arr) shouldBe result
        }
    }
})