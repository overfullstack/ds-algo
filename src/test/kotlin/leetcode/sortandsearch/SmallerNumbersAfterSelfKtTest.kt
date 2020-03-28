package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe
import io.kotest.data.row

class SmallerNumbersAfterSelfKtTest : StringSpec() {

    init {
        "countSmaller" {
            forAll(
                row(intArrayOf(5, 2, 6, 1), intArrayOf(2, 1, 1, 0)),
                row(intArrayOf(1, 2, 3, 4), intArrayOf(0, 0, 0, 0)),
                row(intArrayOf(4, 3, 2, 1), intArrayOf(3, 2, 1, 0)),
                row(intArrayOf(), intArrayOf())
            ) { inputArr, countArr ->
                countSmaller(inputArr) shouldBe countArr
            }
        }
    }

}
