package educative.ll

import ds.ll.ListNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/ll/ReverseNodesInEvenGroups"

class ReverseNodesInEvenGroupsTest :
  StringSpec({
    "reverse nodes in even groups" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<List<Int>>(1) }
        .forAll { (inputs, output) ->
          val head = ListNode.of(inputs.filterNotNull().toIntArray())
          reverseNodesInEvenGroups(head!!).toArray() shouldBe output.toIntArray()
        }
    }
  })
