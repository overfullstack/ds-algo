package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class LongestSubstringWithKDIstinctKtTest : StringSpec() {

    init {
        "lengthOfLongestSubstringKDistinct" {
            lengthOfLongestSubstringKDistinct("eceba", 2) shouldBe 3
            lengthOfLongestSubstringKDistinct("xxyxz", 2) shouldBe 4
        }
    }

}
