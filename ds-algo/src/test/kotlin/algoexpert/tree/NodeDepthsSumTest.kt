package algoexpert.tree

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import ga.overfullstack.ds.tree.TreeNode
import io.kotest.matchers.shouldBe
import ga.overfullstack.utils.TEST_RESOURCES_PATH

private const val PKG_PATH = "$TEST_RESOURCES_PATH/algoexpert/tree/NodeDepthsSum"

class NodeDepthsSumTest : 
  StringSpec({ 
    "node depths" { 
      forAll(
        row(TreeNode.parseJsonFileToTree("$PKG_PATH/tree1.json"), 16),
        row(TreeNode.parseJsonFileToTree("$PKG_PATH/tree2.json"), 0),
        row(TreeNode.parseJsonFileToTree("$PKG_PATH/tree3.json"), 42),
      ) { rootNode, result ->
        rootNode.nodeDepthsSum() shouldBe result
      } } 
    })
