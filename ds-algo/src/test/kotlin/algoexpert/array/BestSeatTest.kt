package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BestSeatTest :
  StringSpec({
    "best seat" {
      forAll(
        row(intArrayOf(1, 0, 1, 0, 0, 0, 1), 4),
        row(intArrayOf(1), -1),
        row(intArrayOf(1, 0, 1), 1),
        row(intArrayOf(1, 0, 0, 1), 1),
        row(intArrayOf(1, 1, 1), -1),
        row(intArrayOf(1, 0, 0, 1, 0, 0, 1), 1),
        row(intArrayOf(1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1), 3),
        row(intArrayOf(1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1), 5),
      ) { seatArr, result ->
        bestSeat(seatArr) shouldBe result
      }
    }
  })
