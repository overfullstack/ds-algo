package algoexpert.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ProductSumTest :
  StringSpec({
    "product sum" {
      forAll(
        row(listOf(5, 2, listOf(7, -1), 3, listOf(6, listOf(-13, 8), 4)), 12),
        row(listOf(1, 2, 3, 4, 5), 15),
        row(listOf(1, 2, listOf(3), 4, 5), 18),
        row(listOf(listOf(listOf(listOf(listOf(5))))), 600),
      ) { splArr: List<Any>, result ->
        productSum(splArr) shouldBe result
      }
    }
  })
