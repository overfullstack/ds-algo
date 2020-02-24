package leetcode.tree

import ds.TreeNode
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class SumRootToLeafKtTest : StringSpec() {

    init {
        "sumFromRootToLeaf" {
            forall(
                row(listOf(1, 2, 3), 25),
                row(listOf(4, 9, 0, 5, 1), 1026),
                row(listOf(4), 4),
                row(listOf(4, 2), 42)
            ) { list, result ->
                TreeNode.listToIncompleteTree(list)!!.sumFromRootToLeaf() shouldBe result
            }
        }
    }

}
