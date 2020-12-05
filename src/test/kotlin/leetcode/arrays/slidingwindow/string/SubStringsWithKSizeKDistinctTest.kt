package leetcode.arrays.slidingwindow.string

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class SubStringsWithKSizeKDistinctTest : StringSpec({
    "subStringsWithKSizeKDistinct" {
        forAll(
            row("abcabc", 3, listOf("abc", "bca", "cab")),
            row("abacab", 3, listOf("bac", "cab")),
            row(
                "awaglknagawunagwkwagl",
                4,
                listOf("wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag")
            )
        ) { s, k, result ->
            subStringsWithKSizeKDistinct(s, k) shouldContainExactlyInAnyOrder result
        }
    }
})
