package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListIntsToInt.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/sorting/cyclicsort/FirstMissingPositiveNumber"

class FirstMissingPositiveNumberTest :
  StringSpec({
    "missing number" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) ->
          firstMissingPositiveNumber(input.toIntArray()) shouldBe output
        }
    }
  })
