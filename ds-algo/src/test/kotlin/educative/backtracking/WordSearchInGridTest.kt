package educative.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListStrToBool.Companion.parseJsonFileToTestCases

/* 29 Jun 2025 11:13 */

private const val PKG_PATH = "educative/backtracking/WordSearch"

class WordSearchTest :
  StringSpec({
    "word search" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (gridL, word, result) ->
          val grid = gridL.map { it.joinToString("").toCharArray() }.toTypedArray()
          wordSearchInGrid(grid, word) shouldBe result
        }
    }
  })
