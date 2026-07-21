package educative.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 30 Jun 2025 14:35 */

private const val PKG_PATH = "educative/backtracking/NQueens"

class NQueensTest :
  StringSpec({
    "n Queens" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<Int>(1) to case.output<Int>(1) }
        .forAll { (n, output) -> nQueensCombinationCount(n) shouldBe output }
    }
  })
