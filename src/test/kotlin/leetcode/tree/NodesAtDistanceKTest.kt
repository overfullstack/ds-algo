package leetcode.tree

import ds.tree.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class NodesAtDistanceKTest : StringSpec({
    "distanceK" {
        forAll(
            row(listOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 5, 2, listOf(7, 4, 1)),
            row(listOf(0, 1, null, 3, 2), 2, 1, listOf(1)),
            row(listOf(0, 5, 1, null, null, 2, 6, null, 3, null, null, 4, null, 7), 7, 3, listOf(2))
        ) { treeList: List<Int?>, targetValue: Int, K: Int, result: List<Int> ->
            distanceK(
                TreeNode.levelOrderToTree(treeList),
                TreeNode(targetValue),
                K
            ) shouldContainExactlyInAnyOrder result
        }
    }
})
