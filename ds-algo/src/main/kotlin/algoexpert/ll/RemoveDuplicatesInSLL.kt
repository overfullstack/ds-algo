package algoexpert.ll

import ds.ll.ListNode

fun ListNode.removeDuplicates(): ListNode {
  var curPtr = this
  var nextPtr = this.next
  while (nextPtr != null) {
    while (nextPtr != null && nextPtr.value == curPtr.value) {
      nextPtr = nextPtr.next
    }
    curPtr.next = nextPtr
    nextPtr?.let { curPtr = it }
  }
  return this
}
