package leetcode.arrays.slidingwindow

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LongestSubStrAtMostKDistinctTest : StringSpec({
    "Longest SubString with at most K Distinct Chars" {
        forAll(
            row("eceba", 3, 4),
            row("WORLD", 4, 4)
        ) { s, k, result ->
            lengthOfLongestSubstringAtmostKDistinct(s, k) shouldBe result
        }
    }
})
