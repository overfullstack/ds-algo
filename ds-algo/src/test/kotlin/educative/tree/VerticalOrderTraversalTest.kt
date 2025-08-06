package educative.tree

import ds.tree.TreeNode.Companion.levelOrderToIncompleteTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToLList.Companion.parseJsonFileToTestCases

/* 19 Jul 2025 18:28 */

private const val PKG_PATH = "educative/tree/VerticalOrderTraversal"

class VerticalOrderTraversalTest :
  StringSpec({
    "Vertical Order Traversal" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        val treeNode = levelOrderToIncompleteTree(input)!!
        treeNode.verticalTraversal() shouldBe output
      }
    }
  })
