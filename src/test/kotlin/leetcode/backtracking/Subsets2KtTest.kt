package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

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
