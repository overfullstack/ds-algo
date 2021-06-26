package cci.trees

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MinimalTreeTest : StringSpec({
  "Minimal Tree" {
    forAll(
      row(intArrayOf(), null),
      row(intArrayOf(1), 1),
      row(intArrayOf(1, 2, 3), 2),
      row(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3),
    ) { arr, result ->
      arrToBST(arr)?.height() shouldBe result
    }
  }
})
