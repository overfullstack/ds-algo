package educative.ll

import ds.ll.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListIntToList.Companion.parseJsonFileToTestCases

/* 27 Jul 2025 18:29 */

private const val PKG_PATH = "educative/ll/RemoveKthToLast"

class RemoveKthToLastTest :
  StringSpec({
    "Remove kth to last" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (inputs, k, output) ->
          val head = SLLNode.of(inputs.toIntArray())!!
          head.removeKthToLast(k).toArray() shouldBe output.toIntArray()
        }
    }
  })
