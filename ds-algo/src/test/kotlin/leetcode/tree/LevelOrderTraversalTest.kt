package leetcode.tree

import ga.overfullstack.ds.tree.TreeNode.Companion.levelOrderToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LevelOrderTraversalTest :
  StringSpec({
    "Level Order Traversal" {
      forAll(
        row(listOf(1, 2, 3, 4, 5, 6, 7), listOf(listOf(1), listOf(2, 3), listOf(4, 5, 6, 7))),
        row(listOf(1), listOf(listOf(1))),
        row(listOf(1, 2, 3, null, null, 4, null), listOf(listOf(1), listOf(2, 3), listOf(4)))
      ) { levelOrder, result ->
        val root = levelOrderToTree(levelOrder)
        val listOfDepths = root!!.levelOrderTraversal()
        listOfDepths.map { levelList -> levelList.map { it.value } } shouldBe result
      }
    }
  })
