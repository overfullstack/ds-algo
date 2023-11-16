package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ArrayOfProductsTest :
  StringSpec({
    "array of products" {
      forAll(
        row(intArrayOf(5, 1, 4, 2), intArrayOf(8, 40, 10, 20)),
        row(intArrayOf(1, 8, 6, 2, 4), intArrayOf(384, 48, 64, 192, 96)),
        row(intArrayOf(-5, 2, -4, 14, -6), intArrayOf(672, -1680, 840, -240, 560)),
        row(intArrayOf(4, 4), intArrayOf(4, 4)),
      ) { arr, resultArr ->
        arr.productArray() shouldBe resultArr
      }
    }
  })
