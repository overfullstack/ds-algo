package cci.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe


class OneAwayKtTest : StringSpec({
    "one way Test" {
        forAll(
            row("", "", true),
            row("", "abc", false),
            row("  ", "abc", false),
            row("  ", "  ", true),
            row("abcde", "abc", false),
            row("abc", "ab", true),
            row("abc", "abcd", true),
            row("pale", "bake", false)
        ) { str1, str2, result ->
            isOneAway(str1, str2) shouldBe result
        }
    }
})
