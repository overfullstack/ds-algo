package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases
import utils.toPair

/* 16 Sep 2024 17:43 */
private const val PKG_PATH = "educative/fusion/InsertInterval"

class InsertIntervalTest :
  StringSpec({
    "insert interval" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          (case.input<List<List<Int>>>(1).map { it.toPair() } to case.input<List<Int>>(2).toPair()) to
            case.output<List<List<Int>>>(1).map { it.toPair() }
        }
        .forAll { (input, result) ->
          val (intervals, newInterval) = input
          insertInterval(intervals, newInterval) shouldBe result
        }
    }
  })
