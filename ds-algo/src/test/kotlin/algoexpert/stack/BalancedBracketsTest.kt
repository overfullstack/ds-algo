package algoexpert.array

import algoexpert.stack.areBracketsBalanced
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class BalancedBracketsTest :
  StringSpec({
    "are brackets balanced" {
      forAll(
        row("([])(){}(())()()", true),
        row("()[]{}{", false),
        row("(((((({{{{{[[[[[([)])]]]]]}}}}}))))))", false),
        row("(141[])(){waga}((51afaw))()hh()", false),
      ) { bracketsStr, result ->
        areBracketsBalanced(bracketsStr) shouldBe result
      }
    }
  })
