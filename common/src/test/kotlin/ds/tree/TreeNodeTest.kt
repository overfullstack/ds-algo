package ds.tree

import ds.tree.TreeNode.Companion.levelOrderToCompleteTree
import ds.tree.TreeNode.Companion.levelOrderToIncompleteTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe

class TreeNodeTest :
  StringSpec({
    "serialize/deserialize tree" {
      listOf(
          listOf(1, 2, null, 3, null, 4, null, 5),
          listOf(1, null, 2, null, 3, null, 4, null, 5),
          listOf(1, null, null),
        )
        .forAll { levelOrder ->
          val root = levelOrderToIncompleteTree(levelOrder)
          root!!.incompleteTreeToLevelOrderList() shouldContainInOrder levelOrder
        }
    }

    "height" {
      val levelOrder = listOf(1, 2, 3, 4, 5, 6, 7)
      val root = levelOrderToCompleteTree(levelOrder)
      root!!.height() shouldBe 3
    }

    "Set Parents" {
      val levelOrder = listOf(1, 2, 3, 4)
      val root = levelOrderToCompleteTree(levelOrder)
      root!!.parent shouldBe null
      root.left!!.parent = root
      root.right!!.parent = root
      root.left!!.left!!.parent = root.left
    }

    "Find Node with value" {
      forAll(
        row(listOf(1, 2, 3, 4, 5, 6, 7), 2, TreeNode(2, TreeNode(4), TreeNode(5))),
        row(listOf(1, 2, 3, 4, 5, 6, 7), 5, TreeNode(5)),
      ) { levelOrder, valToFind, result ->
        val root = levelOrderToCompleteTree(levelOrder)
        root!!.getNodeWithValue(valToFind) shouldBe result
      }
    }
  })
