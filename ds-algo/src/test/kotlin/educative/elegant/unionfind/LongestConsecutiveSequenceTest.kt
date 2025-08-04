package educative.elegant.unionfind

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.ListToInt.Companion.parseJsonFileToTestCases

/* 27 Aug 2024 15:40 */

private const val PKG_PATH = "educative/graph/unionfind/LongestConsecutiveSequence"

class LongestConsecutiveSequenceTest :
  StringSpec({
    "Longest Consecutive Sequence" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, output) ->
        longestConsecutiveSequence(input.filterNotNull().toIntArray()) shouldBe output
      }
    }
  })
