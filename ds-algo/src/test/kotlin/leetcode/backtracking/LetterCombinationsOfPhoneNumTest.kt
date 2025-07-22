package leetcode.backtracking

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe

class LetterCombinationsOfPhoneNumTest :
  StringSpec({
    "letterCombinations" {
      letterCombinations("23") shouldContainExactlyInAnyOrder
        listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")
      letterCombinations("") shouldBe emptyList()
    }
  })
