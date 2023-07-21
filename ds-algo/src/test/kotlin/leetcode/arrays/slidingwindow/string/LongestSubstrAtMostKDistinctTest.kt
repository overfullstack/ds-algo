package leetcode.arrays.slidingwindow.string

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LongestSubstrAtMostKDistinctTest :
  StringSpec({
    "LongestSubstrAtMostKDistinctTest" {
      forAll(
        row("abcbbbbcccbdddadacb", 2, "bcbbbbcccb"),
        row("aabbcc", 1, "aa"),
        row("aabbcc", 2, "aabb"),
        row("aabbcc", 3, "aabbcc")
      ) { str, k, result ->
        lengthOfLongestSubstringAtmostKDistinct(str, k) shouldBe result
      }
    }
  })
