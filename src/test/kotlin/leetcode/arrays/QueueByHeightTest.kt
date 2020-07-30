package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class QueueByHeightTest : StringSpec({
    "Queue reconstruction by height" {
        forAll(
            row(
                arrayOf(
                    intArrayOf(7, 0),
                    intArrayOf(4, 4),
                    intArrayOf(7, 1),
                    intArrayOf(5, 0),
                    intArrayOf(6, 1),
                    intArrayOf(5, 2)
                ),
                arrayOf(
                    intArrayOf(5, 0),
                    intArrayOf(7, 0),
                    intArrayOf(5, 2),
                    intArrayOf(6, 1),
                    intArrayOf(4, 4),
                    intArrayOf(7, 1)
                )
            )
        ) { people, result ->
            reconstructQueue(people).forEachIndexed { i, exp -> exp shouldBe result[i] }
        }
    }
})
