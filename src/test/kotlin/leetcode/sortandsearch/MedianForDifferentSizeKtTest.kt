package leetcode.sortandsearch

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class MedianForDifferentSizeKtTest : StringSpec() {

    init {
        "medianWithDifferentSize" {
            forall(
                row(intArrayOf(1, 3), intArrayOf(2), 2.0),
                row(intArrayOf(1, 2), intArrayOf(3, 4), 2.5)
            ) { a, b, result ->
                medianWithDifferentSize(a, b) shouldBe result
            }
        }
    }

}