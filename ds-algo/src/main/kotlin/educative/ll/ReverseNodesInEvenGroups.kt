package educative.ll

import ds.ll.SLLNode

/* 23 Aug 2024 11:55 */

fun reverseInEvenGroups(head: SLLNode): SLLNode {
  var prevToGrp: SLLNode? = head
  var grpSize = 2
  while (prevToGrp?.next?.next != null) {
    val (noOfNodes, nodeAfter) = prevToGrp.getNodeAfterOrLast(grpSize)
    if (grpSize % 2 == 0 || (grpSize - noOfNodes) % 2 == 0) {
      val nextToGrp = nodeAfter.next
      val rgTail = prevToGrp.next
      val rgHead = rgTail?.reverseInGroup(grpSize)
      prevToGrp.next = rgHead
      rgTail?.next = nextToGrp
      prevToGrp = rgTail
    } else {
      prevToGrp = nodeAfter
    }
    grpSize++
  }
  return head
}

fun SLLNode.reverseInGroup(groupSize: Int, prev: SLLNode? = null): SLLNode =
  when {
    next == null || groupSize == 1 -> {
      next = prev
      this
    }
    else -> {
      val curNext = next
      next = prev
      curNext!!.reverseInGroup(groupSize - 1, this)
    }
  }

fun SLLNode.getNodeAfterOrLast(stepCount: Int): Pair<Int, SLLNode> =
  when {
    next == null || stepCount == 0 -> stepCount to this
    else -> next!!.getNodeAfterOrLast(stepCount - 1)
  }
