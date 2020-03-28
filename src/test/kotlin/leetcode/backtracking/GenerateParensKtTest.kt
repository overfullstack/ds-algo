package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class GenerateParensKtTest : StringSpec() {

    init {
        "generateParenthesis" {
            generateParenthesis(3) shouldContainExactlyInAnyOrder listOf(
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()"
            )
            generateParenthesis(0) shouldBe emptyList()
        }
    }

}
