package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToInt.Companion.parseJsonFileToTestCases

/* 10 Aug 2025 19:40 */

private const val PKG_PATH = "educative/dp/LongestIncreasingSubsequence"

class LongestIncreasingSubsequenceTest :
  StringSpec({
    "longest increasing subsequence" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        longestSubsequence(input.filterNotNull().toIntArray()) shouldBe output
      }
    }
  })
