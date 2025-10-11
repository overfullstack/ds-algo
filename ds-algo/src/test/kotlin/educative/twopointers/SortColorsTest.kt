package educative.twopointers

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToList.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/twopointers/SortColors"

class SortColorsTest :
  StringSpec({
    "sort colors" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (inputs, output) ->
          sortColors(inputs.filterNotNull().toIntArray()) shouldBe output.toIntArray()
        }
    }
  })
