package educative.backtracking

import educative.graph.floodFill
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListIntIntIntToLList.Companion.parseJsonFileToTestCases

/* 29 Jun 2025 11:13 */

private const val PKG_PATH = "educative/backtracking/FloodFill"

class FloodFillTest :
  StringSpec({
    "flood fill" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (gridL, args, result) ->
          val (sr, sc, target) = args
          val grid = gridL.map { it.toIntArray() }.toTypedArray()
          floodFill(grid, sr, sc, target) shouldBe result
        }
    }
  })
