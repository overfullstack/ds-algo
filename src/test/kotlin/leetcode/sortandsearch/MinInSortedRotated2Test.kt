package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinInSortedRotated2Test :
  StringSpec({
    "Min in Sorted Roated with Duplicates" {
      forAll(
        row(intArrayOf(1, 3, 5), 1),
        row(intArrayOf(2, 2, 2, 0, 1), 0),
      ) { arr, result ->
        findMinInSortedRotated2(arr) shouldBe result
      }
    }
  })
