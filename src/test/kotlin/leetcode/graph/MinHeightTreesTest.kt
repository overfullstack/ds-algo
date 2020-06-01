package leetcode.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class MinHeightTreesTest : StringSpec({

    "findMinHeightTrees" {
        forAll(
            row(4, arrayOf(intArrayOf(1, 0), intArrayOf(1, 2), intArrayOf(1, 3)), listOf(1))
        ) { n, edges, result ->
            findMinHeightTrees(n, edges) shouldContainExactlyInAnyOrder result
        }
    }
})
