package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 26 Jul 2025 15:55 */

private const val PKG_PATH = "educative/dp/01Knapsack"

class `01KnapsackTest` :
  StringSpec({
    "0/1 Knapsack" {
      TestCases.load("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<Int>(1), case.input<List<Int>>(2), case.input<List<Int>>(3)) to
            case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (capacity, weights, values) = input
          findMaxKnapsackProfit(capacity, weights.toIntArray(), values.toIntArray()) shouldBe output
        }
    }

    "0/1 Knapsack Top down" {
      TestCases.load("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<Int>(1), case.input<List<Int>>(2), case.input<List<Int>>(3)) to
            case.output<Int>(1)
        }
        .forAll { (input, output) ->
          val (capacity, weights, values) = input
          findMaxKnapsackProfitTopDown(capacity, weights.toIntArray(), values.toIntArray()) shouldBe
            output
        }
    }
  })
