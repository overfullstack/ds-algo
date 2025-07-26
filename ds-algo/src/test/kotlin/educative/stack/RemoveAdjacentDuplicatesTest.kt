package educative.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrToStr.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/stack/RemoveAdjacentDuplicates"

class RemoveAdjacentDuplicatesTest :
  StringSpec({
    "valid parentheses" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        removeAdjacentDuplicates(input) shouldBe output
      }
    }
  })
