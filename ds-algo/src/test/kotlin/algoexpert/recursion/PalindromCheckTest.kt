package algoexpert.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import algoexpert.recursion.isPalindrome

class PalindromeCheckTest :
  StringSpec({
   "check is palindrome" {
       forAll(
         row("abcdcba", true),
         row("a", true),
         row("ab", false),
         row("aba", true),
         row("abcdefghhgfedcba", true),
         row("abcdefghihgfedcba", true),
         row("abcdefghihgfeddcba", false),
       ) { str, result ->
          str.isPalindrome() shouldBe result
       }
  }
})
