package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 26 Jul 2025 13:13 */

private const val PKG_PATH = "educative/dp/MaxProductSubarray"

class MaxProductSubarrayTest :
  StringSpec({
    "Max Product Subarray" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<Int>(1) }
        .forAll { (input, output) ->
          maxProductSubarray(input.filterNotNull().toIntArray()) shouldBe output
        }
    }
  })
