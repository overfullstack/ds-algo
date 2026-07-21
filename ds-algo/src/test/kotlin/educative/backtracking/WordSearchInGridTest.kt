package educative.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 29 Jun 2025 11:13 */

private const val PKG_PATH = "educative/backtracking/WordSearch"

class WordSearchTest :
  StringSpec({
    "word search" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<List<List<String>>>(1), case.input<String>(2), case.output<Boolean>(1))
        }
        .forAll { (gridL, word, result) ->
          val grid = gridL.map { it.joinToString("").toCharArray() }.toTypedArray()
          wordSearchInGrid(grid, word) shouldBe result
        }
    }
  })
