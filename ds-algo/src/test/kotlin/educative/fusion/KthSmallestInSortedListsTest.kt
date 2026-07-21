package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 06 Aug 2025 09:17 */

private const val PKG_PATH = "educative/fusion/KthSmallestInSortedLists"

class KthSmallestInSortedListsTest :
  StringSpec({
    "kth smallest in sorted lists" {
      TestCases.load("$PKG_PATH/test-cases-1.json")
        .map { case ->
          Triple(case.input<List<List<Int>>>(1), case.input<Int>(2), case.output<Int>(1))
        }
        .forAll { (lists, k, result) ->
          kSmallestNumber(lists, k) shouldBe result
        }
    }
  })
