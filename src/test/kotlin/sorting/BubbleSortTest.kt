package sorting

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BubbleSortTest : StringSpec({
    "bubbleSort" {
        forAll(
            row(intArrayOf(3, 2, 4, 1), intArrayOf(1, 2, 3, 4)),
            row(intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1)),
            row(intArrayOf(), intArrayOf())
        ) { arr, result ->
            BubbleSort.bubbleSort(arr) shouldBe result
        }
    }
})
