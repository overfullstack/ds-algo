package educative.graph

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.MatrixToMatrix.Companion.parseJsonFileToTestCases

/* 23/7/25 17:53 */

private const val PKG_PATH = "educative/graph/PacificAtlanticWaterFlow"

class PacificAtlanticWaterFlowTest :
  StringSpec({
    "Pacific Atlantic Water Flow" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json").forAll { (grid, output) ->
        estimateWaterFlow(grid.map { it.toIntArray() }.toTypedArray()) shouldBe output
      }
    }
  })
