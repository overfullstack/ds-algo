package cci.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class StringCompressionKtTest :
  StringSpec({
    "String Compression" {
      forAll(
        row("", ""),
        row("a", "a"),
        row("aa", "aa"),
        row("abcd", "abcd"),
        row("aaabbcc", "a3b2c2"),
      ) { str, result ->
        compressString(str) shouldBe result
      }
    }
  })
