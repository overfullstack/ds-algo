package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.dp.canPartition
import testcase.ListToBool.Companion.parseJsonFileToTestCases

/* 26 Jul 2025 14:55 */

private const val PKG_PATH = "educative/dp/PartitionEqualSubsetSum"

class PartitionEqualSubsetSumTest :
  StringSpec({
    "Partition Subset Sum" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) -> canPartition(input.toIntArray()) shouldBe output }
    }
  })
