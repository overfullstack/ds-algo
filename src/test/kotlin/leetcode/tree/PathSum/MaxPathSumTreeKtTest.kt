package leetcode.tree.PathSum

import ds.TreeNode
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class MaxPathSumTreeKtTest : StringSpec() {

    init {
        "maxPathSum" {
            forall(
                row(listOf(1, 2, 3), 6),
                row(listOf(-10, 9, 20, null, null, 15, 7), 42),
                row(listOf(-3), -3),
                row(listOf(2, -1), 2),
                row(listOf(1, -2, 3), 4),
                row(listOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1), 48)
            ) { list, maxPathSum ->
                TreeNode.listToIncompleteTree(list)!!.maxPathSum().second shouldBe maxPathSum
            }
        }
    }

}
