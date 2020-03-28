package leetcode.tree

import ds.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe
import io.kotest.data.row

class SumRootToLeafKtTest : StringSpec() {

    init {
        "sumFromRootToLeaf" {
            forAll(
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
