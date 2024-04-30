package algoexpert.tree

import ga.overfullstack.ds.tree.TreeNode.Companion.parseJsonFileToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "algoexpert/tree/BinaryTreeDiameter"

class BinaryTreeDiameterTest :
  StringSpec({
    "binary tree diameter" {
      forAll(row(parseJsonFileToTree("$PKG_PATH/tree1.json"), 6)) { root, result ->
        root.diameter().second shouldBe result
      }
    }
  })
