package algoexpert.ll

import ga.overfullstack.ds.ll.SLLNode

fun SLLNode.middleNode(): SLLNode {
  var ptr: SLLNode? = this
  var ptr2: SLLNode? = this

  if (ptr?.next == null) {
    return ptr!!
  }

  while (ptr2?.next != null) {
    ptr = ptr?.next
    ptr2 = ptr2.next?.next
  }
  return ptr!!
}
