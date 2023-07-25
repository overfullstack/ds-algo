package algoexpert.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NonConstructibleChangeTest :
  StringSpec({
    "minimum non-constructable change" {
      forAll(
        row(intArrayOf(5, 7, 1, 1, 2, 3, 22), 20),
        row(intArrayOf(1, 1, 1, 1, 1), 6),
        row(intArrayOf(1, 5, 1, 1, 1, 10, 15, 20, 100), 55),
        row(intArrayOf(), 1),
        row(intArrayOf(87), 1),
        row(intArrayOf(1, 1), 3),
        row(intArrayOf(1), 2),
        row(intArrayOf(2), 1),
        row(intArrayOf(1, 2, 3, 4, 5, 6, 7), 29),
      ) { coins, result ->
        minimumNonConstructableChange(coins) shouldBe result
      }
    }
  })
