package educative.hashing

import educative.stack.nextGreaterElement
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 20 Jul 2025 08:22 */

private const val PKG_PATH = "educative/hashing/NextGreaterElement"

class NextGreaterElementTest :
  StringSpec({
    "next greater element" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          (case.input<List<Int>>(1) to case.input<List<Int>>(2)) to case.output<List<Int?>>(1)
        }
        .forAll { (input, output) ->
          nextGreaterElement(input.first.toIntArray(), input.second.toIntArray()) shouldBe
            output.filterNotNull().toIntArray()
        }
    }
  })
