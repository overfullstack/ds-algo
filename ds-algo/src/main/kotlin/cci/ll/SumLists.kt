package cci.ll

import ga.overfullstack.ds.SLLNode

fun SLLNode?.sumListWith(node: SLLNode?, carry: Int = 0): SLLNode? =
  when {
    this == null && node == null -> if (carry == 0) null else SLLNode(carry)
    this == null -> node?.carryForward(carry)
    node == null -> carryForward(carry)
    else -> {
      val nodeSum = value + node.value + carry
      sumListsWithNext(nodeSum, node)
    }
  }

fun SLLNode.carryForward(carry: Int): SLLNode {
  val nodeSum = value + carry
  return if (nodeSum < 10) {
    also { it.value = nodeSum }
  } else {
    sumListsWithNext(nodeSum)
  }
}

fun SLLNode.sumListsWithNext(nodeSum: Int, node: SLLNode? = null): SLLNode {
  val curCarry = nodeSum / 10
  return SLLNode(nodeSum % 10, next.sumListWith(node?.next, curCarry))
}
