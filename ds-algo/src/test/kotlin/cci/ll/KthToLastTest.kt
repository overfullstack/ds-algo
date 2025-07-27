package cci.ll

import ds.ll.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class KthToLastTest :
  StringSpec({
    "Kth to Last SLL" {
      forAll(
        row(intArrayOf(1, 2, 3, 4), 3, 2),
        row(intArrayOf(1, 2), 1, 2),
        row(intArrayOf(1, 2), 2, 1),
        row(intArrayOf(1), 1, 1),
        row(intArrayOf(1), 2, null),
        row(intArrayOf(), 0, null),
        row(intArrayOf(), 1, null),
      ) { arr, k, result ->
        SLLNode.of(arr)?.getKthToLast(k)?.value shouldBe result
      }
    }
  })
