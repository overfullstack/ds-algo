package cci.ll

import ga.overfullstack.ds.SLLNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class KthToLastTest :
  StringSpec({
    "Kth to Last" {
      forAll(
        row(intArrayOf(1, 2, 3, 4), 2, 2),
        row(intArrayOf(1, 2), 0, 2),
        row(intArrayOf(1, 2), 1, 1),
        row(intArrayOf(1), 0, 1),
        row(intArrayOf(1), 1, null),
        row(intArrayOf(), 0, null),
        row(intArrayOf(), 1, null),
      ) { arr, k, result ->
        SLLNode.of(arr)?.getKthToLast(k)?.value shouldBe result
      }
    }
  })
