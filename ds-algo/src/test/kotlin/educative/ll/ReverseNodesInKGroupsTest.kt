package educative.ll

import ds.ll.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase2.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/ll/ReverseNodesInKGroups"

class ReverseNodesInKGroupsTest :
  StringSpec({
    "reverse nodes in K groups" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (inputs, k, output) ->
          val head = SLLNode.of(inputs.toIntArray())!!
          reverseNodesInKGroups(head, k).toArray() shouldBe output.toIntArray()
        }
    }
  })
