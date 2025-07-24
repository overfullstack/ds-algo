package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListPairToInt.Companion.parseJsonFileToTestCases

/* 12 Jul 2025 18:52 */

private const val PKG_PATH = "educative/heap/ScheduleTasksOnMinimumMachines"

class ScheduleTasksOnMinimumMachinesTest :
  StringSpec({
    "Schedule tasks on minimum machines" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (intervals, output) ->
        minimumMachines(intervals) shouldBe output
      }
    }
  })
