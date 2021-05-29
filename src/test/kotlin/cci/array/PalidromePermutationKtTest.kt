package cci.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PalidromePermutationKtTest : StringSpec({
    "isPalindromePermutation" {
        forAll(
            row("abc", false),
            row("abcc", false),
            row("aabaa", true),
            row("abcd", false),
            row("abab", true),
            row("a", true),
            row("", true)
        ) { str, result ->
            isPalidromePermutation(str) shouldBe result
        }
    }
})
