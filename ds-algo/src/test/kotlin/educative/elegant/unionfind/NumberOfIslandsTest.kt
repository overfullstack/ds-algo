package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 05 Sep 2024 21:31 */

private const val PKG_PATH = "educative/graph/unionfind/NumberOfIslands"

class NumberOfIslandsTest :
  StringSpec({
    "no.of islands" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          case
            .input<List<List<String>>>(1)
            .map { it.map { it.toInt() }.toIntArray() }
            .toTypedArray() to case.output<Int>(1)
        }
        .forAll { (grid, result) ->
          numberOfIslands(grid) shouldBe result
        }
    }
  })
