package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CapacityToShipInDDaysTest : StringSpec({
  "Ship in D days" {
    forAll(
      row(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5, 15),
      row(intArrayOf(3, 2, 2, 4, 1, 4), 3, 6),
      row(intArrayOf(1, 2, 3, 1, 1), 4, 3),
    ) { weights, D, result ->
      shipWithinDays(weights, D) shouldBe result
    }
  }
})
