package educative.backtracking

import ds.tree.TreeNode.Companion.levelOrderToTree
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase16.Companion.parseJsonFileToTestCases

/* 30 Jun 2025 14:35 */

private const val PKG_PATH = "educative/backtracking/HouseRobber3"

class HouseRobber3Test :
  StringSpec({
    "house robber 3" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (inputs, output) ->
          val treeNode = levelOrderToTree(inputs)
          treeNode!!.houseRobber3() shouldBe output
        }
    }
  })
