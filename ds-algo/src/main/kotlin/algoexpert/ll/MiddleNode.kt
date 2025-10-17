package algoexpert.ll

import ds.ll.ListNode

fun ListNode.middleNode(): ListNode {
  var ptr: ListNode? = this
  var ptr2: ListNode? = this

  if (ptr?.next == null) {
    return ptr!!
  }

  while (ptr2?.next != null) {
    ptr = ptr?.next
    ptr2 = ptr2.next?.next
  }
  return ptr!!
}
