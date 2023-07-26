package algoexpert.array

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TandemBicycleTest :
  StringSpec({
    "speed of tandem bicycle" {
      forAll(
        row(intArrayOf(3,6,7,2,1), intArrayOf(5,5,3,9,2), true, 32),
        row(intArrayOf(3,6,7,2,1), intArrayOf(5,5,3,9,2), false, 25),
      ) { redShirtSpeeds, blueShirtSpeeds, fastest, result ->
         speed(redShirtSpeeds, blueShirtSpeeds, fastest) shouldBe result
      }
    }
  })
