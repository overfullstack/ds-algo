package cci.ll

import ds.SLLNode
import ds.toArray
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PartitionTest : StringSpec({
  "Partition" {
    forAll(
      row(intArrayOf(3, 5, 8, 5, 10, 2, 1), 5, intArrayOf(3, 2, 1, 5, 10, 5, 8)),
      row(intArrayOf(1, 2, 3), 5, intArrayOf(1, 2, 3)),
      row(intArrayOf(5, 6, 7), 3, intArrayOf(5, 6, 7)),
      row(intArrayOf(1), 1, intArrayOf(1)),
      row(intArrayOf(), 1, null)
    ) { arr, pivot, result ->
      val node = SLLNode.of(arr)
      node.partition(pivot)
      node?.toArray() shouldBe result
    }
  }
})
