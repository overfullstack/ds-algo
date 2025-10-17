package cci.ll

import ds.ll.ListNode

fun ListNode?.partition(pivot: Int) {
  var gPtr = this
  var lPtr = this
  while (lPtr != null) {
    while (gPtr != null && gPtr.value < pivot) {
      gPtr = gPtr.next
    }
    if (gPtr == null) {
      return
    }
    lPtr = gPtr.next
    while (lPtr != null && lPtr.value >= pivot) {
      lPtr = lPtr.next
    }
    if (lPtr == null) {
      return
    }
    gPtr.swapValue(lPtr)
  }
}

private fun ListNode.swapValue(node: ListNode) {
  value = node.value.also { node.value = value }
}
