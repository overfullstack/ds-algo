package educative.tree

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListListToList.Companion.parseJsonFileToTestCases

/* 29 Jul 2025 12:47 */

private const val PKG_PATH = "educative/tree/TreeFromPreOrderAndInOrder"

class TreeFromPreOrderAndInOrderTest: StringSpec({
  "Tree From Preorder and Inorder" {
    parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json")
      .forAll { (input, output) ->
        buildTree(input.first.toIntArray(), input.second.toIntArray()).second?.incompleteTreeToLevelOrderList() shouldBe output
      }
  }
})
