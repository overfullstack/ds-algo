package cci.ll

import ds.ll.ListNode
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class SumListsTest :
  StringSpec({
    "Sum lists" {
      forAll(
        row(intArrayOf(), intArrayOf(9, 9, 9, 9), intArrayOf(9, 9, 9, 9)),
        row(intArrayOf(7, 1, 6), intArrayOf(5, 9, 2), intArrayOf(2, 1, 9)),
        row(intArrayOf(9, 9), intArrayOf(9, 9), intArrayOf(8, 9, 1)),
        row(intArrayOf(9, 9), intArrayOf(9, 9, 9), intArrayOf(8, 9, 0, 1)),
        row(intArrayOf(9, 9), intArrayOf(9, 9, 9, 9), intArrayOf(8, 9, 0, 0, 1)),
        row(intArrayOf(9, 9), intArrayOf(9, 9, 8, 8), intArrayOf(8, 9, 9, 8)),
      ) { arr1, arr2, result ->
        val node11 = ListNode.of(arr1)
        val node12 = ListNode.of(arr2)
        node11.sumListWith(node12)?.toArray() shouldBe result

        val node21 = ListNode.of(arr1)
        val node22 = ListNode.of(arr2)
        node22.sumListWith(node21)?.toArray() shouldBe result
      }
    }
  })
