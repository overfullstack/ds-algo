package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.LListIntToInt.Companion.parseJsonFileToTestCases

/* 06 Aug 2025 09:17 */

private const val PKG_PATH = "educative/fusion/KthSmallestInSortedLists"

class KthSmallestInSortedListsTest :
  StringSpec({
    "kth smallest in sorted lists" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (lists, k, result) ->
        kSmallestNumber(lists, k) shouldBe result
      }
    }
  })
