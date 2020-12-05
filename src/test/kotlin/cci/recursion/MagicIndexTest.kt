package cci.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MagicIndexTest : StringSpec({
    "Non repeating" {
        forAll(
            row(intArrayOf(-1, 0, 2, 5, 7, 9), 2),
            row(intArrayOf(1, 2, 3, 4, 5, 6), -1)
        ) { arr, result ->
            searchMagicIndexNonRepeating(arr) shouldBe result
        }
    }

    "Repeating" {
        forAll(
            row(intArrayOf(-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13), 2)
        ) { arr, result ->
            findMagicIndexRepeating(arr) shouldBe result
        }
    }
})
