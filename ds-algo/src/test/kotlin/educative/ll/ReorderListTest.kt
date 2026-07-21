package educative.ll

import ds.ll.ListNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/ll/ReorderList"

class ReorderListTest :
  StringSpec({
    "reorder list" {
      TestCases.load("$PKG_PATH/test-cases.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<List<Int>>(1) }
        .forAll { (inputs, output) ->
          val head = ListNode.of(inputs.filterNotNull().toIntArray())
          reorderList(head!!).toArray() shouldBe output.toIntArray()
        }
    }
  })
