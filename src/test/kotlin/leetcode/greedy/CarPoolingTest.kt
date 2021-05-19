package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CarPoolingTest : StringSpec({
    "Car Pooling" {
        forAll(
            row(arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 4, false),
            row(arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 3, 7)), 5, true),
            row(arrayOf(intArrayOf(2, 1, 5), intArrayOf(3, 5, 7)), 3, true),
            row(arrayOf(intArrayOf(3, 2, 7), intArrayOf(3, 7, 9), intArrayOf(8, 3, 9)), 11, true),
        ) { arr, capacity, result ->
            carPooling(arr, capacity) shouldBe result
        }
    }
})
