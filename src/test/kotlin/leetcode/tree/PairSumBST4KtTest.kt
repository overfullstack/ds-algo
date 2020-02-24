package leetcode.tree

import ds.TreeNode
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class PairSumBST4KtTest : StringSpec() {

    init {
        "isPairWithSumPresent" {
            forall(
                row(intArrayOf(5, 3, 6, 2, 4, 7), 9, true),
                row(intArrayOf(5, 3, 6, 2, 4, 7), 28,false)
            ) { arr, targetSum, result ->
                TreeNode.arrToBST(arr)!!.isPairWithSumPresent(targetSum) shouldBe result
            }
        }
    }

}
