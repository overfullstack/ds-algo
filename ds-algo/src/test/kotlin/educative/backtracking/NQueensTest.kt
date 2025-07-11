package educative.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase17.Companion.parseJsonFileToTestCases

/* 30 Jun 2025 14:35 */

private const val PKG_PATH = "educative/backtracking/NQueens"

class NQueensTest :
  StringSpec({
    "n Queens" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (n, output) ->
        nQueens(n) shouldBe output
      }
    }
  })
