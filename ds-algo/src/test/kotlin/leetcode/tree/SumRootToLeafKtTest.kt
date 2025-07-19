package leetcode.tree

import ds.tree.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SumRootToLeafKtTest : StringSpec() {

  init {
    "sumFromRootToLeaf" {
      forAll(
        row(listOf(1, 2, 3), 25),
        row(listOf(4, 9, 0, 5, 1), 1026),
        row(listOf(4), 4),
        row(listOf(4, 2), 42),
      ) { list, result ->
        TreeNode.levelOrderToIncompleteTree(list)!!.sumFromRootToLeaf() shouldBe result
      }
    }
  }
}
