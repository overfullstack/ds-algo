package leetcode.arrays

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class LongestSubStringNoRepeatKtTest : StringSpec() {

    init {
        "lengthOfLongestSubstringWithoutCharRepeat" {
            forall(
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