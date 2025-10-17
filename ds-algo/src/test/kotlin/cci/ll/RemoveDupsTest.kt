package cci.ll

import ds.ll.ListNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RemoveDupsTest :
  StringSpec({
    "Remove Dups" {
      forAll(
        row(intArrayOf(1, 2, 1, 3, 3, 2, 4, 4), intArrayOf(1, 2, 3, 4)),
        row(intArrayOf(1, 2, 3, 4), intArrayOf(1, 2, 3, 4)),
        row(intArrayOf(1), intArrayOf(1)),
        row(intArrayOf(1, 1), intArrayOf(1)),
        row(intArrayOf(), null),
      ) { arrWithDups, result ->
        val node = ListNode.of(arrWithDups)
        node?.removeDupsFromSLL()
        node?.toArray() shouldBe result
      }
    }
  })
