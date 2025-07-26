package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.IntListListToInt.Companion.parseJsonFileToTestCases

/* 26 Jul 2025 15:55 */

private const val PKG_PATH = "educative/dp/01Knapsack"

class `01KnapsackTest` :
  StringSpec({
    "0/1 Knapsack" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) ->
          val (capacity, weights, values) = input
          findMaxKnapsackProfit(capacity, weights.toIntArray(), values.toIntArray()) shouldBe output
        }
    }
  })
