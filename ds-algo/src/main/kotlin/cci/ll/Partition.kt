package cci.ll

import ga.overfullstack.ds.ll.SLLNode

fun SLLNode?.partition(pivot: Int) {
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

private fun SLLNode.swapValue(node: SLLNode) {
  value = node.value.also { node.value = value }
}
