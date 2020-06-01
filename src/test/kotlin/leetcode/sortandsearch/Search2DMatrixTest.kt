package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class Search2DMatrixTest: StringSpec({
    "Search in 2D Matrix" {
        forAll(
            row(arrayOf(intArrayOf(1,   3,  5,  7), intArrayOf(10, 11, 16, 20), intArrayOf(23, 30, 34, 50)), 3, true),
            row(arrayOf(intArrayOf(1,   3,  5,  7), intArrayOf(10, 11, 16, 20), intArrayOf(23, 30, 34, 50)), 13, false),
        ) { matrix, target, result ->
            searchIn2dMatrix(matrix, target) shouldBe result
        }
    }
})