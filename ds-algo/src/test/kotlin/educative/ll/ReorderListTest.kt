package educative.ll

import ds.ll.ListNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToList.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/ll/ReorderList"

class ReorderListTest :
  StringSpec({
    "reorder list" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases.json").forAll { (inputs, output) ->
        val head = ListNode.of(inputs.filterNotNull().toIntArray())
        reorderList(head!!).toArray() shouldBe output.toIntArray()
      }
    }
  })
