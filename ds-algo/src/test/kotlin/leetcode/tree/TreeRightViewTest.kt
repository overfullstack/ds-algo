package leetcode.tree

import ds.tree.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainInOrder

class TreeRightViewTest :
  StringSpec({
    "Tree Right view" {
      forAll(row(listOf(1, 2, 3, 4), listOf(1, 3, 4))) { levelOrder, result ->
        TreeNode.levelOrderToCompleteTree(levelOrder)!!.rightSideView() shouldContainInOrder result
      }
    }
  })
