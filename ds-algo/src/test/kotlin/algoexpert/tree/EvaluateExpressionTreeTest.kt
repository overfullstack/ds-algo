package algoexpert.tree

import ga.overfullstack.ds.tree.TreeNode.Utils.parseJsonFileToTree
import ga.overfullstack.utils.TEST_RESOURCES_PATH
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

private const val PKG_PATH = "$TEST_RESOURCES_PATH/algoexpert/tree/EvaluateExpressionTree"

class EvaluateExpressionTreeTest :
  StringSpec({
    "evaluate expression tree" {
      forAll(
        row(parseJsonFileToTree("$PKG_PATH/tree1.json"), 5),
        row(parseJsonFileToTree("$PKG_PATH/tree2.json"), 14)
      ) { root, result ->
        root.evaluateExpressionTree() shouldBe result
      }
    }
  })
