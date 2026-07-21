package educative.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import testcase.TestCases

/* 26 Jul 2025 21:57 */

private const val PKG_PATH = "educative/dp/DecodeWays"

class DecodeWaysTest :
  StringSpec({
    "decode Ways Bottoms Up" {
      TestCases.load("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<String>(1) to case.output<Int>(1) }
        .forAll { (input, output) -> numOfDecodingsBottomsUp(input) shouldBe output }
    }

    "decode Ways Top Down" {
      TestCases.load("${PKG_PATH}/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<String>(1) to case.output<Int>(1) }
        .forAll { (input, output) -> numOfDecodingsTopDown(input) shouldBe output }
    }
  })
