package algoexpert.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NthFibonacciTest :
  StringSpec({
    "nth fibonacci" {
      forAll(
        row(8, 13),
        row(7, 8),
        row(6, 5),
        row(5, 3),
        row(4, 2),
        row(3, 1),
        row(2, 1),
        row(1, 0),
      ) { n, result ->
        nthFibonacci(n) shouldBe result
      }
    }
  })
