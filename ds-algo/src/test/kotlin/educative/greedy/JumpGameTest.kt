package educative.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import leetcode.greedy.canJump
import testcase.TestCases

/* 13 Jul 2025 11:41 */

private const val PKG_PATH = "educative/greedy/JumpGame"

class JumpGameTest :
  StringSpec({
    "Jump Game" {
      TestCases.load("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .map { case -> case.input<List<Int>>(1) to case.output<Boolean>(1) }
        .forAll { (input, output) -> canJump(input.toIntArray()) shouldBe output }
    }
  })
