package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.dp.canPartition
import testcase.TestCases

/* 26 Jul 2025 14:55 */

private const val PKG_PATH = "educative/dp/PartitionEqualSubsetSum"

class PartitionEqualSubsetSumTest :
  StringSpec({
    "Partition Subset Sum" {
      TestCases.load("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<List<Int>>(1) to case.output<Boolean>(1) }
        .forAll { (input, output) -> canPartition(input.toIntArray()) shouldBe output }
    }
  })
