package leetcode.backtracking

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.specs.StringSpec

class Subsets2KtTest : StringSpec() {

    init {
        "subsetsWithDup" {
            val subsetsWithDup = subsetsWithDup(intArrayOf(1, 2, 2))
            val expected = listOf(
                listOf(2),
                listOf(1),
                listOf(1, 2, 2),
                listOf(2, 2),
                listOf(1, 2)
            )
            subsetsWithDup shouldContainExactlyInAnyOrder expected
        }
    }

}