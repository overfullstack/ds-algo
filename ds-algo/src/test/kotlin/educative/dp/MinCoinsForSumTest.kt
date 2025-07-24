package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListIntToInt.Companion.parseJsonFileToTestCases

/* 24/7/25 18:35 */

private const val PKG_PATH = "educative/dp/MinCoinsForSum"

class MinCoinsForSumTest :
  StringSpec({
    "Minimum Coins For Sum" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        minCoinsForSum(input.first.toIntArray(), input.second) shouldBe output
      }
    }
  })
