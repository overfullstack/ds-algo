package educative.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 12 Jul 2025 18:52 */

private const val PKG_PATH = "educative/heap/ScheduleTasksOnMinimumMachines"

class ScheduleTasksOnMinimumMachinesTest :
  StringSpec({
    "Schedule tasks on minimum machines" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case -> case.input<List<List<Int>>>(1).map { it.toPair() } to case.output<Int>(1) }
        .forAll { (intervals, output) -> minimumMachines(intervals) shouldBe output }
    }
  })
