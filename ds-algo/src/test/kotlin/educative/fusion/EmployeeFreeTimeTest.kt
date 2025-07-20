package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase29.Companion.parseJsonFileToTestCases

/* 20 Jul 2025 19:21 */

private const val PKG_PATH = "educative/fusion/EmployeeFreeTime"

class EmployeeFreeTimeTest :
  StringSpec({
    "employee free time" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, result) ->
        employeeFreeTime(input) shouldBe result
      }
    }
  })
