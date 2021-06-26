package leetcode.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SmallestSubsequenceDistinctCharsTest : StringSpec({

  "Smallest Subsequence Distinct Chars" {
    forAll(
      row("bcabc", "abc"),
      row("cbacdcbc", "acdb")
    ) { text, result ->
      smallestSubsequence(text) shouldBe result
    }
  }
})
