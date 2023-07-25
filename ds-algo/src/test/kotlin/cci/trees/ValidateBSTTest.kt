package cci.trees

import ga.overfullstack.ds.tree.TreeNode.Companion.levelOrderToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ValidateBSTTest :
  StringSpec({
    "valid BST" {
      forAll(
        row(listOf(1, 2, 3), false),
        row(listOf(2, 1, 3), true),
        row(listOf(3, 2, 4, 1, null, null, 5), true),
        row(listOf(3, 2, 4, 1, null, null, 3), false)
      ) { levelOrder, result ->
        levelOrderToTree(levelOrder).validateBST() shouldBe result
      }
    }
  })
