package algoexpert.tree

import ds.tree.TreeNode.Companion.parseJsonFileToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

private const val PKG_PATH = "algoexpert/tree/BranchSum"

class BranchSumTest :
  StringSpec({
    "Branch Sum Test" {
      forAll(
        row(parseJsonFileToTree("$PKG_PATH/tree1.json"), listOf(15, 16, 18, 10, 11)),
        row(parseJsonFileToTree("$PKG_PATH/tree2.json"), listOf(1)),
        row(parseJsonFileToTree("$PKG_PATH/tree3.json"), listOf(15, 16, 18, 9, 11, 11, 11)),
      ) { root, result ->
        root.branchSum() shouldContainExactlyInAnyOrder result
      }
    }
  })
