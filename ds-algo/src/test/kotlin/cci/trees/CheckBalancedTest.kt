package cci.trees

import ds.tree.TreeNode.Companion.levelOrderToIncompleteTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import leetcode.tree.checkBalanced

class CheckBalancedTest :
  StringSpec({
    "Check Balanced" {
      forAll(
        row(listOf(1, 2, 3, 4, 5, 6, 7), true),
        row(listOf(1, 2, 3, 4, null, null, null, 5), false),
        row(listOf(1), true),
      ) { levelOrder, result ->
        levelOrderToIncompleteTree(levelOrder).checkBalanced().second shouldBe result
      }
    }
  })
