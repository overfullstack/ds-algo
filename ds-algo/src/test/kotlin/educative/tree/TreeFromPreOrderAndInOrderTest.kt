package educative.tree

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 29 Jul 2025 12:47 */

private const val PKG_PATH = "educative/tree/TreeFromPreOrderAndInOrder"

class TreeFromPreOrderAndInOrderTest :
  StringSpec({
    "Tree From Preorder and Inorder" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          (case.input<List<Int>>(1) to case.input<List<Int>>(2)) to case.output<List<Int?>>(1)
        }
        .forAll { (input, output) ->
          buildTree(input.first.toIntArray(), input.second.toIntArray())
            .second
            ?.incompleteTreeToLevelOrderList() shouldBe output
        }
    }
  })
