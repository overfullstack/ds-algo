package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 12 Jul 2025 18:52 */

private const val PKG_PATH = "educative/heap/MaximizeCapital"

class MaximizeCapitalTest :
  StringSpec({
    "Maximize Capital" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(
            case.input<Int>(1),
            case.input<Int>(2),
            case.input<List<Int>>(3).zip(case.input<List<Int>>(4)),
          ) to case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (initialCapital, k, capitalsToProfits) = input
          maximizeCapital(initialCapital, k, capitalsToProfits) shouldBe output
        }
    }
  })
