package educative.fusion

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import testcase.ListListIntToLList.Companion.parseJsonFileToTestCases
import utils.toPair

/* 06 Aug 2025 11:31 */

private const val PKG_PATH = "educative/fusion/KPairsWithSmallestSum"

class KPairsWithSmallestSumTest :
  StringSpec({
    "k Pairs With Smallest Sum" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json").forAll { (input, result) ->
        val (nums1, nums2, k) = input
        kPairsWithSmallestSum(
          nums1.toIntArray(),
          nums2.toIntArray(),
          k,
        ) shouldContainExactlyInAnyOrder result.map { it.toPair() }
      }
    }
  })
