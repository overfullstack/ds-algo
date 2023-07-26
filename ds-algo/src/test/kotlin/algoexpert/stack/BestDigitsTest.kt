package algoexpert.stack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BestDigitsTest :
  StringSpec({
    "best digits" {
      forAll(
        row("462839", 2, "6839"),
        row("129847563", 4, "98763"),
      ) { str, numDigits, result ->
         bestDigits(str, numDigits) shouldBe result
      }
    }
  })
