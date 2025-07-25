package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import testcase.StrListToList.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "educative/dp/WordBreak2"

class WordBreakSentencesTest :
  StringSpec({
    "Word Break Sentences" {
      forAll(
        row(
          "catsanddog",
          listOf("cat", "cats", "and", "sand", "dog"),
          listOf("cats and dog", "cat sand dog"),
        ),
        row(
          "pineapplepenapple",
          listOf("apple", "pen", "applepen", "pine", "pineapple"),
          listOf("pine apple pen apple", "pineapple pen apple", "pine applepen apple"),
        ),
      ) { word, wordDict, result ->
        wordBreakSentences(word, wordDict) shouldContainExactlyInAnyOrder result
      }
    }

    "Word Break Sentences 2" {
      parseJsonFileToTestCases("$PKG_PATH/test-cases-1.json", "$PKG_PATH/test-cases-2.json")
        .forAll { (input, result) ->
          val (sentence, dict) = input
          wordBreakSentences(sentence, dict) shouldContainExactlyInAnyOrder result
        }
    }
  })
