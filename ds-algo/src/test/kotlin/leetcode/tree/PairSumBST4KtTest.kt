package leetcode.tree

import ds.tree.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PairSumBST4KtTest : StringSpec() {

  init {
    "isPairWithSumPresent" {
      forAll(
        row(intArrayOf(5, 3, 6, 2, 4, 7), 9, true),
        row(intArrayOf(5, 3, 6, 2, 4, 7), 28, false)
      ) { arr, targetSum, result ->
        TreeNode.arrToBST(arr)!!.isPairWithSumPresent(targetSum) shouldBe result
      }
    }
  }
}
