package educative.hashing

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IsomorphicStringsTest :
  StringSpec({
    "isIsomorphic" {
      forAll(
        row("egg", "add", true),
        row("foo", "bar", false),
        row("paper", "title", true),
        row("badc", "baba", false),
        row("", "", true),
        row("a", "a", true),
        row("ab", "aa", false),
        row("aa", "ab", false),
      ) { s, t, result ->
        isIsomorphic(s, t) shouldBe result
      }
    }
  })
