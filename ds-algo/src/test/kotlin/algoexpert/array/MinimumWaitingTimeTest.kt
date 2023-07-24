package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinimumWaitingTimeTest :
  StringSpec({
    "minmum waiting time" {
      forAll(
        row(intArrayOf(3, 2, 1, 2, 6), 17),
      ) { timeArr, result ->
        minimumWaitingTime(timeArr) shouldBe result
      }
    }
  })
