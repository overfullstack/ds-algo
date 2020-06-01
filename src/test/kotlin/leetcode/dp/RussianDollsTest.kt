package leetcode.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RussianDollsTest: StringSpec({
    "Maximum envelops to Russian Doll" {
        forAll(
            row(arrayOf(intArrayOf(5,4), intArrayOf(6,4), intArrayOf(6,7), intArrayOf(2,3)), 3),
            row(arrayOf(), 0),
            row(arrayOf(intArrayOf(1,1), intArrayOf(1,1)), 1)
        ) { arr, result ->
            maxEnvelops(arr) shouldBe result
        }
    }
})