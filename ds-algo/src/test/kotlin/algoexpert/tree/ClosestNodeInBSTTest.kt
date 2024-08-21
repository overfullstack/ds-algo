package algoexpert.tree

import ds.tree.TreeNode.Companion.parseJsonFileToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "algoexpert/tree/ClosestNodeInBST"

class ClosestNodeInBSTTest :
  StringSpec({
    "closest in BST" {
      val root1 = parseJsonFileToTree("$PKG_PATH/tree1.json")
      val root2 = parseJsonFileToTree("$PKG_PATH/tree2.json")
      forAll(
        row(root1, 12, 13),
        row(root2, 208, 208),
        row(root2, 4501, 4500),
        row(root2, -1, 1),
        row(root2, -70, -51),
      ) { rootNode, valueToSearch, result ->
        rootNode.closestNodeInBST(valueToSearch) shouldBe result
      }
    }
  })
