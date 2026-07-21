package educative.twopointers

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 28 Jul 2025 08:53 */

private const val PKG_PATH = "educative/twopointers/ContainerWithMostWater"

class ContainerWithMostWaterTest :
  StringSpec({
    "container with most water" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<Int>(1) }
        .forAll { (inputs, output) ->
          containerWithMostWater(inputs.filterNotNull().toIntArray()) shouldBe output
        }
    }
  })
