package educative.backtracking

import educative.graph.floodFill
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 29 Jun 2025 11:13 */

private const val PKG_PATH = "educative/backtracking/FloodFill"

class FloodFillTest :
  StringSpec({
    "flood fill" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(
            case.input<List<List<Int>>>(1),
            Triple(case.input<Int>(2), case.input<Int>(3), case.input<Int>(4)),
            case.output<List<List<Int>>>(1).map { it.toIntArray() }.toTypedArray()
          )
        }
        .forAll { (gridL, args, result) ->
          val (sr, sc, target) = args
          val grid = gridL.map { it.toIntArray() }.toTypedArray()
          floodFill(grid, sr, sc, target) shouldBe result
        }
    }
  })
