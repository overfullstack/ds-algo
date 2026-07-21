package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/dp/LongestPalindromicSubstring"

class LongestPalindromicSubstringTest :
  StringSpec({
    "valid parentheses" {
      TestCases.load("$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<String>(1) to case.output<String>(1) }
        .forAll { (input, output) ->
          longestPalindromicSubstring(input) shouldBe output
        }
    }
  })
