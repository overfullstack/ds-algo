package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IslandsTest : StringSpec({
    "Number of islands" {
        forAll(
            row(arrayOf("11110".toCharArray(), "11010".toCharArray(), "11000".toCharArray(), "00000".toCharArray()), 1),
        ) { grid, result ->
            numIslands(grid) shouldBe result
        }
    }
})
