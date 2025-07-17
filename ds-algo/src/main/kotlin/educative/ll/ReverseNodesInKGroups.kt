package educative.ll

import ds.ll.SLLNode

/* 24 Aug 2024 11:21 */

fun reverseNodesInKGroups(head: SLLNode, k: Int): SLLNode {
  val dummyHead = SLLNode(Int.MIN_VALUE, head)
  var prevToGrp: SLLNode? = dummyHead
  while (prevToGrp?.next?.next != null) {
    val (noOfNodesInGrp, grpEndNode) = prevToGrp.getNodeAfterOrLast(k)
    when (noOfNodesInGrp) {
      k -> {
        val nextToGrp = grpEndNode.next
        val rgTail = prevToGrp.next
        rgTail?.reverseInGroup(nextToGrp, k)
        prevToGrp.next = grpEndNode
        prevToGrp = rgTail
      }
      else -> break
    }
  }
  return dummyHead.next!!
}
