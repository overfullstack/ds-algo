package leetcode.tree

import ds.TreeNode
import io.kotlintest.data.suspend.forall
import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class NodesAtDistanceKKtTest : StringSpec() {

    init {
        "distanceK" {
            forall(
                row(listOf(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4), 5, 2, listOf(7, 4, 1)),
                row(listOf(0, 1, null, 3, 2), 2, 1, listOf(1)),
                row(listOf(0,5,1,null,null,2,6,null,3,null,null,4,null,7), 7, 3, listOf(2))
            ) { treeList, targetValue, K, result ->
                distanceK(
                    TreeNode.listToIncompleteTree(treeList),
                    TreeNode(targetValue),
                    K
                ) shouldContainExactlyInAnyOrder result
            }
        }
    }

}
