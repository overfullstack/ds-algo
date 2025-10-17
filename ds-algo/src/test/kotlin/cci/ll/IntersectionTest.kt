package cci.ll

import ds.ll.ListNode
import ds.ll.last
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class IntersectionTest :
  StringSpec({
    "Intersection" {
      forAll(
        row(intArrayOf(1, 2, 3), intArrayOf(4), intArrayOf(5, 9, 10), 5),
        row(intArrayOf(1), intArrayOf(1), intArrayOf(), 1),
        row(intArrayOf(), intArrayOf(1), intArrayOf(2), null),
      ) { half1, half2, intersection, result ->
        val half1Node: ListNode? = ListNode.of(half1)
        val half2Node = ListNode.of(half2)
        val intersectionNode = ListNode.of(intersection)

        half1Node?.last()?.next = intersectionNode
        half2Node.last()?.next = intersectionNode

        half1Node?.getIntersectionNode(half2Node)?.value shouldBe result
        half2Node?.getIntersectionNode(half1Node)?.value shouldBe result
      }
    }
  })
