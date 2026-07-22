package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 24/7/25 18:35 */

private const val PKG_PATH = "educative/dp/MinCoinsForSum"

class MinCoinsForSumTest :
  StringSpec({
    "Minimum Coins For Sum" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> (case.input<List<Int>>(1) to case.input<Int>(2)) to case.output<Int>(1) }
        .forAll { (input, output) ->
          coinChange(input.first.toIntArray(), input.second) shouldBe output
        }
    }
  })
