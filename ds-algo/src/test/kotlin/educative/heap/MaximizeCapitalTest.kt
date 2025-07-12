package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase19.Companion.parseJsonFileToTestCases

/* 12 Jul 2025 18:52 */

private const val PKG_PATH = "educative/heap/MaximizeCapital"

class MaximizeCapitalTest :
  StringSpec({
    "Maximize Capital" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        val (initialCapital, k, capitalsToProfits) = input
        maximizeCapital(initialCapital, k, capitalsToProfits) shouldBe output
      }
    }
  })
