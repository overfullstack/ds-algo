package cci.trees

import ds.tree.TreeNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SuccessorTest :
  StringSpec({
    "Find Inorder Successor" {
      forAll(
        row(listOf(1, 2, 3, null, null, 4), 1, 4),
        row(listOf(1, 2, 3, null, null, 4), 2, 1),
        row(listOf(1, 2, 3, null, null, 4), 3, null)
      ) { levelOrder, nodeVal, result ->
        val root = TreeNode.levelOrderToTree(levelOrder)
        root!!.setParents()
        val node = root.getNodeWithValue(nodeVal)
        node?.successor()?.value shouldBe result
      }
    }
  })
