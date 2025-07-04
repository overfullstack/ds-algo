package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MedianForDifferentSizeKtTest : StringSpec() {

  init {
    "medianWithDifferentSize" {
      forAll(
        row(intArrayOf(1, 3), intArrayOf(2), 2.0),
        row(intArrayOf(1, 2), intArrayOf(3, 4), 2.5),
      ) { a, b, result ->
        findMedianSortedArrays(a, b) shouldBe result
      }
    }
  }
}
