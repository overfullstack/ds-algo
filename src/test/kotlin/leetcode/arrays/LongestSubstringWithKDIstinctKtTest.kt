package leetcode.arrays

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class LongestSubstringWithKDIstinctKtTest : StringSpec() {

    init {
        "lengthOfLongestSubstringKDistinct" {
            lengthOfLongestSubstringKDistinct("eceba", 2) shouldBe 3
        }
    }

}