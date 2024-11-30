package educative.graph.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase11.Companion.parseJsonFileToTestCases

/* 10 Sep 2024 14:03 */

private const val PKG_PATH = "educative/graph/unionfind/LastDayToCross"

class LastDayToCrossTest :
  StringSpec({
    "last day to cross" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, result) ->
        val (rows, cols, waterCells) = input
        lastDayToCross(rows, cols, waterCells) shouldBe result
      }
    }
  })
