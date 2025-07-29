package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListListToLList.Companion.parseJsonFileToTestCases

/* 16 Sep 2024 17:43 */
private const val PKG_PATH = "educative/fusion/InsertInterval"

class InsertIntervalTest :
  StringSpec({
    "insert interval" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, result) ->
        val (intervals, newInterval) = input
        insertInterval(intervals, newInterval) shouldBe result
      }
    }
  })
