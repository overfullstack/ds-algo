package leetcode.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class StocksTest :
  StringSpec({
    "maxProfit" {
      forAll(row(intArrayOf(10, 7, 5, 8, 11, 9), 6)) { prices, result ->
        maxProfit(prices) shouldBe result
      }
    }
  })
