package leetcode.backtracking

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.specs.StringSpec

class LetterCombinationsKtTest : StringSpec() {

    init {
        "letterCombinations" {
            letterCombinations("23") shouldContainExactlyInAnyOrder
                    listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
        }
    }

}