package educative.ll

import ds.ll.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase.Companion.parseJsonFileToReorderListArgs

private const val PKG_PATH = "educative/ll/ReorderList"

class ReorderListTest :
  StringSpec({
    "reorder list" {
      parseJsonFileToReorderListArgs("$PKG_PATH/test-cases.json").forAll { (inputs, output) ->
        val head = SLLNode.of(inputs.toIntArray())
        reorderList(head!!).toArray() shouldBe output.toIntArray()
      }
    }
  })
