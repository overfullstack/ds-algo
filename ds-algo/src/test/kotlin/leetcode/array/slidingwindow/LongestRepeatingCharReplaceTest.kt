package leetcode.array.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import leetcode.slidingwindow.characterReplacement

class LongestRepeatingCharReplaceTest :
  StringSpec({
    "Longest Repeating Char Replace" {
      forAll(row("AABABBA", 1, 4)) { s, k, result -> characterReplacement(s, k) shouldBe result }
    }
  })
