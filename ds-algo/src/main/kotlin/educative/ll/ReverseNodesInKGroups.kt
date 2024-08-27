package educative.ll

import ds.ll.SLLNode

/* 24 Aug 2024 11:21 */

fun reverseNodesInKGroups(head: SLLNode, k: Int): SLLNode {
  var dummyHead = SLLNode(Int.MIN_VALUE, head)
  var prevToGrp: SLLNode? = dummyHead
  while (prevToGrp?.next?.next != null) {
    val (noOfNodesInGrp, grpEndNode) = prevToGrp.getNodeAfterOrLast(k)
    if (noOfNodesInGrp == k) {
      var nextToGrp = grpEndNode.next
      val rgTail = prevToGrp.next
      var rgHead = rgTail?.reverseInGroup(k, nextToGrp)
      prevToGrp.next = rgHead
      prevToGrp = rgTail
    } else {
      break
    }
  }
  return dummyHead.next!!
}
