package educative.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrToBool.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/stack/ValidParentheses"

class ValidParenthesesTest :
  StringSpec({
    "valid parentheses" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        validParentheses(input) shouldBe output
      }
    }
  })
