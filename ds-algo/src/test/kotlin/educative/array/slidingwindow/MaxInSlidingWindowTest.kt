package educative.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase2.Companion.parseJsonFileToTestCases

/* 04 Sep 2024 16:55 */

private const val PKG_PATH = "educative/array/slidingWindow/MaxInSlidingWindow"

class MaxInSlidingWindowTest :
  StringSpec({
    "min window subsequence" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (inputs, k, output) ->
          maxInSlidingWindow(inputs.toIntArray(), k) shouldBe output
        }
    }
  })
