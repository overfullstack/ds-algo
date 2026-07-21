package leetcode.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.slidingwindow.slidingWindowMax
import testcase.TestCases

/* 04 Sep 2024 16:55 */

private const val PKG_PATH = "educative/array/slidingWindow/MaxInSlidingWindow"

class SlidingWindowMaxTest :
  StringSpec({
    "min window subsequence" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case ->
          Triple(case.input<List<Int>>(1), case.input<Int>(2), case.output<List<Int>>(1))
        }
        .forAll { (inputs, k, output) -> slidingWindowMax(inputs.toIntArray(), k) shouldBe output }
    }
  })
