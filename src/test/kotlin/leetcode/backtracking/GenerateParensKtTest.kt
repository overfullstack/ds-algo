package leetcode.backtracking

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class GenerateParensKtTest : StringSpec() {

    init {
        "generateParenthesis" { 
            generateParenthesis(3) shouldContainExactlyInAnyOrder listOf(
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()")
            generateParenthesis(0) shouldBe emptyList()
        }
    }

}
