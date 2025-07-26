package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrToStr.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/dp/LongestPalindromicSubstring"

class LongestPalindromicSubstringTest :
  StringSpec({
    "valid parentheses" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-2.json").forAll { (input, output) ->
        longestPalindromicSubstring(input) shouldBe output
      }
    }
  })
