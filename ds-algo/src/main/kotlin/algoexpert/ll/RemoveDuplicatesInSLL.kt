package algoexpert.ll

import ds.ll.SLLNode

fun SLLNode.removeDuplicates(): SLLNode {
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
