package educative.hashing

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase26.Companion.parseJsonFileToTestCases

/* 20 Jul 2025 08:22 */

private const val PKG_PATH = "educative/hashing/NextGreaterElement"

class NextGreaterElementTest :
  StringSpec({
    "next greater element" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json", "${PKG_PATH}/test-cases-2.json")
        .forAll { (input, output) ->
          nextGreaterElement(input.first.toIntArray(), input.second.toIntArray()) shouldBe
            output.toIntArray()
        }
    }
  })
