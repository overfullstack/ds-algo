package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class WordBreakSentencesTest :
  StringSpec({
    "Word Break Sentences" {
      withData(
        Triple(
          "catsanddog",
          listOf("cat", "cats", "and", "sand", "dog"),
          listOf("cats and dog", "cat sand dog")
        ),
        Triple(
          "pineapplepenapple",
          listOf("apple", "pen", "applepen", "pine", "pineapple"),
          listOf("pine apple pen apple", "pineapple pen apple", "pine applepen apple")
        )
      ) { (word, wordDict, result) ->
        wordBreakSentences(word, wordDict) shouldContainExactlyInAnyOrder result
      }
    }
  })
