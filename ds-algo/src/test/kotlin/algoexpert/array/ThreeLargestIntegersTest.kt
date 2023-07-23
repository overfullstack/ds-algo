package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.collections.shouldContainExactly

class ThreeLargestIntegersTest :
  StringSpec({
    "three largest integers"  {
      forAll(
        row(intArrayOf(141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7), listOf(18, 141, 541)),
        row(intArrayOf(55, 7, 8), listOf(7, 8, 55)),
        row(intArrayOf(55,43,11,3,-3,10), listOf(11, 43, 55)),
        row(intArrayOf(-1,-2,-3,-7,-17,-27,-18,-541,-8,-7,7), listOf(-2, -1, 7)),
      ) { arr, result ->
        threeLargestIntegers(arr) shouldContainExactly result
      }
    }
  })
