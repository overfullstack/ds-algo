package educative.ll

import ds.ll.SLLNode

/* 23 Aug 2024 11:55 */

fun reverseInEvenGroups(head: SLLNode): SLLNode {
  var prevToGrp: SLLNode? = head
  var grpSize = 2
  while (prevToGrp?.next?.next != null) {
    val (stepCount, grpEndNode) = prevToGrp.getNodeAfterOrLast(grpSize)
    if (grpSize % 2 == 0 || stepCount % 2 == 0) {
      val nextToGrp = grpEndNode.next
      val rgTail = prevToGrp.next
      val rgHead = rgTail?.reverseInGroup(grpSize, nextToGrp)
      prevToGrp.next = rgHead
      prevToGrp = rgTail
    } else {
      prevToGrp = grpEndNode
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

fun SLLNode.getNodeAfterOrLast(stepCount: Int, curStepCount: Int = 0): Pair<Int, SLLNode> =
  when {
    next == null || stepCount == curStepCount -> curStepCount to this
    else -> next!!.getNodeAfterOrLast(stepCount, curStepCount + 1)
  }
