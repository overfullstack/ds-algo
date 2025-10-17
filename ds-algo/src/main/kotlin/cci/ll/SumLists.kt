package cci.ll

import ds.ll.ListNode

fun ListNode?.sumListWith(node: ListNode?, carry: Int = 0): ListNode? =
  when {
    this == null && node == null -> if (carry == 0) null else ListNode(carry)
    this == null -> node?.carryForward(carry)
    node == null -> carryForward(carry)
    else -> {
      val nodeSum = value + node.value + carry
      sumListsWithNext(nodeSum, node)
    }
  }

fun ListNode.carryForward(carry: Int): ListNode {
  val nodeSum = value + carry
  return if (nodeSum < 10) {
    also { it.value = nodeSum }
  } else {
    sumListsWithNext(nodeSum)
  }
}

fun ListNode.sumListsWithNext(nodeSum: Int, node: ListNode? = null): ListNode {
  val curCarry = nodeSum / 10
  return ListNode(nodeSum % 10, next.sumListWith(node?.next, curCarry))
}
