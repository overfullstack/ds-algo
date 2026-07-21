package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 20 Jul 2025 19:21 */

private const val PKG_PATH = "educative/fusion/EmployeeFreeTime"

class EmployeeFreeTimeTest :
  StringSpec({
    "employee free time" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          case.input<List<List<List<Int>>>>(1).map { it.map { it.toPair() } } to
            case.output<List<List<Int>>>(1).map { it.toPair() }
        }
        .forAll { (allEmployeeMeetings, result) ->
          employeeFreeTime(allEmployeeMeetings) shouldBe result
        }
    }
  })
