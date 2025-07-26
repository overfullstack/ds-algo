package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToInt.Companion.parseJsonFileToTestCases

/* 26 Jul 2025 13:13 */

private const val PKG_PATH = "educative/dp/MaxProductSubarray"

class MaxProductSubarrayTest :
  StringSpec({
    "Max Product Subarray" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) ->
          maxProductSubarray(input.filterNotNull().toIntArray()) shouldBe output
        }
    }
  })
