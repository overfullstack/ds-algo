package educative.ll

import ds.ll.SLLNode

/* 23 Aug 2024 11:55 */

fun reverseNodesInEvenGroups(head: SLLNode): SLLNode {
  var prevGrpTail: SLLNode? = head
  var grpSizeToReverse = 2
  while (prevGrpTail?.next?.next != null) { // Break if only one or none left
    val (actualGrpSize, grpEndNode) = prevGrpTail.getNodeAfterOrLast(grpSizeToReverse)
    if (grpSizeToReverse % 2 == 0 || actualGrpSize % 2 == 0) {
      val nextGrpHead = grpEndNode.next
      val revGrpTail = prevGrpTail.next
      revGrpTail?.reverseInGroup(nextGrpHead, actualGrpSize) // revGrpHead
      prevGrpTail.next = grpEndNode // grpEndNode points to revGrpHead
      prevGrpTail = revGrpTail
    } else {
      prevGrpTail = grpEndNode
    }
    grpSizeToReverse++
  }
  return head
}

fun SLLNode.reverseInGroup(prev: SLLNode?, actualGrpSize: Int): SLLNode =
  when {
    actualGrpSize == 1 -> {
      next = prev
      this
    }
    else -> {
      val curNext = next
      next = prev
      curNext!!.reverseInGroup(this, actualGrpSize - 1)
    }
  }

fun SLLNode.getNodeAfterOrLast(stepCount: Int, curStepCount: Int = 0): Pair<Int, SLLNode> =
  when {
    next == null || stepCount == curStepCount -> curStepCount to this
    else -> next!!.getNodeAfterOrLast(stepCount, curStepCount + 1)
  }
