package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToInt.Companion.parseJsonFileToTestCases

/* 27 Aug 2024 15:40 */

private const val PKG_PATH = "educative/sorting/cyclicsort/MissingNumber"

class MissingNumberTest :
  StringSpec({
    "missing number" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases.json").forAll { (input, output) ->
        missingNumber(input.filterNotNull().toIntArray()) shouldBe output
      }
    }
  })
