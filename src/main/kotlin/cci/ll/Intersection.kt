package cci.ll

import ds.SLLNode
import ds.getNodeAt
import ds.length

fun SLLNode.getIntersectionNode(that: SLLNode?): SLLNode? {
  val thisLen = length()
  val thatLen = that?.length() ?: 0
  return if (thisLen > thatLen) {
    this.getMatchingNode(thisLen - thatLen, that)
  } else {
    that?.getMatchingNode(thatLen - thisLen, this)
  }
}

private fun SLLNode.getMatchingNode(headStart: Int, that: SLLNode?): SLLNode? =
  getNodeAt(headStart)?.getMatchingNode(that)

private tailrec fun SLLNode.getMatchingNode(that: SLLNode?): SLLNode? =
  when {
    that == null -> null
    value == that.value -> this
    else -> next?.getMatchingNode(that.next)
  }
