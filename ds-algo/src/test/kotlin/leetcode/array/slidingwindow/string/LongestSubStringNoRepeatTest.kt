package leetcode.array.slidingwindow.string

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import leetcode.slidingwindow.string.lengthOfLongestSubstringWithoutCharRepeat

class LongestSubStringNoRepeatTest :
  StringSpec({
    "Length Of Longest Substring without Char Repeat" {
      forAll(
        row("abcabcbb", 3),
        row("bbbbb", 1),
        row("pwwkew", 3),
        row("abc", 3),
        row("", 0),
        row(" ", 1),
      ) { str, result ->
        lengthOfLongestSubstringWithoutCharRepeat(str) shouldBe result
      }
    }
  })
