package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.dp.jobScheduling
import leetcode.dp.jobScheduling2
import testcase.List3ToInt.Companion.parseJsonFileToTestCases

/* 24/7/25 13:30 */

private const val PKG_PATH = "educative/dp/MaxProfitInJobScheduling"

class MaxProfitInJobSchedulingTest :
  StringSpec({
    "Max profit in job scheduling" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, result) ->
          val (startTimeArr, endTimeArr, profitArr) = input
          jobScheduling(startTimeArr, endTimeArr, profitArr) shouldBe result
        }
    }

    "Max profit in job scheduling perf" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-3.json").forAll { (input, _) ->
        val (startTimeArr, endTimeArr, profitArr) = input
        println(jobScheduling(startTimeArr, endTimeArr, profitArr))
      }
    }

    "Max profit in job scheduling 2" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, result) ->
          val (startTimeArr, endTimeArr, profitArr) = input
          jobScheduling2(startTimeArr, endTimeArr, profitArr) shouldBe result
        }
    }

    "Max profit in job scheduling perf 2" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-3.json").forAll { (input, _) ->
        val (startTimeArr, endTimeArr, profitArr) = input
        println(jobScheduling2(startTimeArr, endTimeArr, profitArr))
      }
    }
  })
