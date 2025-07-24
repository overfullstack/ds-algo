package leetcode.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

@ExperimentalStdlibApi
class LargestRectangleInHistogramTest :
  StringSpec({
    "Largest Rectangle" {
      forAll(
        row(arrayOf(1, 2, 3, 4, 5), 9),
        row(arrayOf(6, 2, 5, 4, 5, 1, 6), 12),
        row(arrayOf(2, 1, 5, 6, 2, 3), 10),
        row(arrayOf(), 0),
      ) { arr, result ->
        largestRectangle(arr) shouldBe result
      }
    }
  })
