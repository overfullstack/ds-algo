package educative.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCase2
import testcase.TestCase8
import testcase.TestCase8.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/array/slidingWindow/MinWindowSubsequence"

class MinWindowSubsequenceTest :
  StringSpec({
    "max in sliding window" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (str1, str2, result) ->
          minWindowSubsequence(str1, str2) shouldBe result
        }
    }
  })
