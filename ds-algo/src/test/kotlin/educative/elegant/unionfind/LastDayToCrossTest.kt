package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 10 Sep 2024 14:03 */

private const val PKG_PATH = "educative/graph/unionfind/LastDayToCross"

class LastDayToCrossTest :
  StringSpec({
    "last day to cross" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(
            case.input<Int>(1),
            case.input<Int>(2),
            case.input<List<List<Int>>>(3).map { it.toPair() }.toSet(),
          ) to case.output<Int>(1)
        }
        .forAll { (input, result) ->
          val (rows, cols, waterCells) = input
          lastDayToCross(rows, cols, waterCells) shouldBe result
        }
    }
  })
