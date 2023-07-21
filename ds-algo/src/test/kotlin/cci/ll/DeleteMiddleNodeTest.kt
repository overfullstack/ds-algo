package cci.ll

import ga.overfullstack.ds.SLLNode
import ga.overfullstack.ds.getNodeForValue
import ga.overfullstack.ds.toArray
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DeleteMiddleNodeTest :
  StringSpec({
    "Delete Middle Node" {
      forAll(
        row(intArrayOf(1, 2, 3, 4), 3, intArrayOf(1, 2, 4)),
        row(intArrayOf(1, 2, 3), 2, intArrayOf(1, 3)),
        row(intArrayOf(1, 2, 3), 4, intArrayOf(1, 2, 3)),
        row(intArrayOf(), 4, null)
      ) { arr, valueToDelete, result ->
        val sll = SLLNode.of(arr)
        val nodeToDelete = sll?.getNodeForValue(valueToDelete)
        nodeToDelete?.deleteMe()
        sll?.toArray() shouldBe result
      }
    }
  })
