package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase28.Companion.parseJsonFileToTestCases

/* 16 Sep 2024 17:43 */
private const val PKG_PATH = "educative/fusion/IntervalListIntersections"

class IntervalListIntersectionsTest :
  StringSpec({
    "interval List Intersections" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, result) ->
          val (a, b) = input
          intervalListIntersections(a, b) shouldBe result
        }
    }
  })
