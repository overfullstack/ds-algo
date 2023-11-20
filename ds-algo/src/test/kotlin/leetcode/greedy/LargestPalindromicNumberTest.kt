package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LargestPalindromicNumberTest :
  StringSpec({
    "largestPalindromicNumberTest" {
      forAll(
        row("444947137", "7449447"),
        row("00009", "9"),
        row("00000", "0"),
        row("6006", "6006"),
      ) { input, result ->
        largestPalindromicNumber(input) shouldBe result
      }
    }
  })
