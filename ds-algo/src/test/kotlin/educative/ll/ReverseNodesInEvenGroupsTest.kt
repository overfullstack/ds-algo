package educative.ll

import ds.ll.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase1.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/ll/ReverseNodesInEvenGroups"

class ReverseNodesInEvenGroupsTest :
  StringSpec({
    "reverse nodes in even groups" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (inputs, output) ->
        val head = SLLNode.of(inputs.toIntArray())
        reverseNodesInEvenGroups(head!!).toArray() shouldBe output.toIntArray()
      }
    }
  })
