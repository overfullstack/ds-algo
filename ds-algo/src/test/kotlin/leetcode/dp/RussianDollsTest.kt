package leetcode.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RussianDollsTest :
  StringSpec({
    "Maximum envelops to Russian Doll" {
      forAll(
        row(listOf(intArrayOf(5, 4), intArrayOf(6, 4), intArrayOf(6, 7), intArrayOf(2, 3)), 3),
        row(listOf(), 0),
        row(listOf(intArrayOf(1, 1), intArrayOf(1, 1)), 1),
      ) { arr, result ->
        maxEnvelops(arr.toTypedArray()) shouldBe result
      }
    }
  })
