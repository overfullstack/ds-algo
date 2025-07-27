package cci.ll

import ds.ll.SLLNode
import ds.ll.getNodeAtOrNull
import ds.ll.length

fun SLLNode.getIntersectionNode(that: SLLNode?): SLLNode? {
  val thisLen = length()
  val thatLen = length()
  return when {
    thisLen > thatLen -> this.getMatchingNode(thisLen - thatLen + 1, that)
    else -> that?.getMatchingNode(thatLen - thisLen + 1, this)
  }
}

private fun SLLNode.getMatchingNode(nthNodeToStart: Int, that: SLLNode?): SLLNode? =
  getNodeAtOrNull(nthNodeToStart)?.getMatchingNode(that)

private tailrec fun SLLNode.getMatchingNode(that: SLLNode?): SLLNode? =
  when {
    that == null -> null
    value == that.value -> this
    else -> next?.getMatchingNode(that.next)
  }
