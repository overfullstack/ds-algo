package leetcode.sortandsearch

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class SmallerNumbersAfterSelfKtTest : StringSpec() {

    init {
        "countSmaller" {
            forall(
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