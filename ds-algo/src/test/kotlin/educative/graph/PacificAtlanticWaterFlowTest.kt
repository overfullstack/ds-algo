package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 23/7/25 17:53 */

private const val PKG_PATH = "educative/graph/PacificAtlanticWaterFlow"

class PacificAtlanticWaterFlowTest :
  StringSpec({
    "Pacific Atlantic Water Flow" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          case.input<List<List<Int>>>(1) to
            case.output<List<List<Int>>>(1).map { it.toPair() }.toSet()
        }
        .forAll { (grid, output) ->
          estimateWaterFlow(grid.map { it.toIntArray() }.toTypedArray()) shouldBe output
        }
    }
  })
