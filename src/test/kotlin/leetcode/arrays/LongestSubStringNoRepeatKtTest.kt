package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe
import io.kotest.data.row

class LongestSubStringNoRepeatKtTest : StringSpec() {

    init {
        "lengthOfLongestSubstringWithoutCharRepeat" {
            forAll(
                row("abcabcbb", 3),
                row("bbbbb", 1),
                row("pwwkew", 3),
                row("", 0)
            ) { str, result ->
                lengthOfLongestSubstringWithoutCharRepeat(str) shouldBe result
            }
        }
    }

}
