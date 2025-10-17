package cci.ll

import ds.ll.ListNode
import ds.ll.getNodeAtOrNull
import ds.ll.length

fun ListNode.getIntersectionNode(that: ListNode?): ListNode? {
  val thisLen = length()
  val thatLen = length()
  return when {
    thisLen > thatLen -> this.getMatchingNode(thisLen - thatLen + 1, that)
    else -> that?.getMatchingNode(thatLen - thisLen + 1, this)
  }
}

private fun ListNode.getMatchingNode(nthNodeToStart: Int, that: ListNode?): ListNode? =
  getNodeAtOrNull(nthNodeToStart)?.getMatchingNode(that)

private tailrec fun ListNode.getMatchingNode(that: ListNode?): ListNode? =
  when {
    that == null -> null
    value == that.value -> this
    else -> next?.getMatchingNode(that.next)
  }
