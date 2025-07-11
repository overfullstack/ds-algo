package educative.graph.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase9.Companion.parseJsonFileToTestCases

/* 05 Sep 2024 21:31 */

private const val PKG_PATH = "educative/graph/unionfind/NumberOfIslands"

class NumberOfIslandsTest :
  StringSpec({
    "no.of islands" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (grid, result) ->
        numberOfIslands(grid) shouldBe result
      }
    }
  })
