package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.StrToInt.Companion.parseJsonFileToTestCases

/* 26 Jul 2025 21:57 */

private const val PKG_PATH = "educative/dp/DecodeWays"

class DecodeWaysTest :
  StringSpec({
    "decode Ways Bottoms Up" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) -> numOfDecodingsBottomsUp(input) shouldBe output }
    }

    "decode Ways Top Down" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, output) -> numOfDecodingsTopDown(input) shouldBe output }
    }
  })
