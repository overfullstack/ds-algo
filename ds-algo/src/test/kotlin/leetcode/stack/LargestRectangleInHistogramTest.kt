package leetcode.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToInt.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/stack/LargestRectangleInHistogram"

class LargestRectangleInHistogramTest :
  StringSpec({
    "Largest Rectangle" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        largestRectangle(input.filterNotNull().toIntArray()) shouldBe output
      }
    }

    "Largest Rectangle 2" {
      forAll(
        row(arrayOf(1, 2, 3, 4, 5), 9),
        row(arrayOf(6, 2, 5, 4, 5, 1, 6), 12),
        row(arrayOf(2, 1, 5, 6, 2, 3), 10),
        row(arrayOf(), 0),
      ) { arr, result ->
        largestRectangle(arr.toIntArray()) shouldBe result
      }
    }
  })
