package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe
import io.kotest.data.row

class MedianForDifferentSizeKtTest : StringSpec() {

    init {
        "medianWithDifferentSize" {
            forAll(
                row(intArrayOf(1, 3), intArrayOf(2), 2.0),
                row(intArrayOf(1, 2), intArrayOf(3, 4), 2.5)
            ) { a, b, result ->
                medianWithDifferentSize(a, b) shouldBe result
            }
        }
    }

}
