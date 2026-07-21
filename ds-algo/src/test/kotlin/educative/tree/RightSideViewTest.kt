package educative.tree

import ds.tree.TreeNode.Companion.levelOrderToIncompleteTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.tree.rightSideView
import testcase.TestCases

/* 28 Jul 2025 19:30 */

private const val PKG_PATH = "educative/tree/RightSideView"

class RightSideViewTest :
  StringSpec({
    "Right Side View" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<List<Int>>(1) }
        .forAll { (input, output) ->
          val treeNode = levelOrderToIncompleteTree(input)
          (treeNode?.rightSideView() ?: emptyList()) shouldBe output
        }
    }
  })
