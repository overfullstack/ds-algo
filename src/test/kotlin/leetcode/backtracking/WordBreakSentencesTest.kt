package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class WordBreakSentencesTest : StringSpec({
  "Word Break Sentences" {
    forAll(
      row(
        "catsanddog",
        listOf("cat", "cats", "and", "sand", "dog"),
        listOf("cats and dog", "cat sand dog")
      ),
      row(
        "pineapplepenapple", listOf("apple", "pen", "applepen", "pine", "pineapple"),
        listOf("pine apple pen apple", "pineapple pen apple", "pine applepen apple")
      )
    ) { word, wordDict, result ->
      wordBreakSentences(word, wordDict) shouldContainExactlyInAnyOrder result
    }
  }
})
