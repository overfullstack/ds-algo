package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 10 Aug 2025 18:02 */

private const val PKG_PATH = "educative/fusion/TaskScheduler"

class TaskSchedulerTest :
  StringSpec({
    "least time to finish all tasks" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<List<String>>(1), case.input<Int>(2), case.output<Int>(1))
        }
        .forAll { (strList, coolingPeriod, output) ->
          leastTime(strList.map { it[0] }.toCharArray(), coolingPeriod) shouldBe output
        }
    }
  })
