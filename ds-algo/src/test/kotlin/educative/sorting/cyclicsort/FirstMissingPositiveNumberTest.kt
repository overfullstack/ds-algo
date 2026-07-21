package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

private const val PKG_PATH = "educative/sorting/cyclicsort/FirstMissingPositiveNumber"

class FirstMissingPositiveNumberTest :
  StringSpec({
    "missing number" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<List<Int?>>(1) to case.output<Int>(1) }
        .forAll { (input, output) ->
          firstMissingPositiveNumber(input.filterNotNull().toIntArray()) shouldBe output
        }
    }
  })
