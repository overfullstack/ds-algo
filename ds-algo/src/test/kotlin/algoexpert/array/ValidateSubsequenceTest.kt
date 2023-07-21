package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import isValidSubsequence

class ValidateSubsequenceTest :
  StringSpec({
    "validate subsequence" {
      forAll(
        row(listOf(5, 1, 22, 25, 6, -1, 8, 10), listOf(1, 6, -1, 10), true),
      ) { array, sequence, result ->
        isValidSubsequence(array, sequence) shouldBe result
      }
    }
  })
