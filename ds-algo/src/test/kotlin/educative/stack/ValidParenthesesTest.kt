package educative.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/stack/ValidParentheses"

class ValidParenthesesTest :
  StringSpec({
    "valid parentheses" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<String>(1) to case.output<Boolean>(1) }
        .forAll { (input, output) -> validParentheses(input) shouldBe output }
    }
  })
