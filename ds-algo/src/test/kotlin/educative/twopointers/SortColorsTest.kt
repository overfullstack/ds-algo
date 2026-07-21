package educative.twopointers

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/twopointers/SortColors"

class SortColorsTest :
  StringSpec({
    "sort colors" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<List<Int>>(1) }
        .forAll { (inputs, output) ->
          sortColors(inputs.filterNotNull().toIntArray()) shouldBe output.toIntArray()
        }
    }
  })
