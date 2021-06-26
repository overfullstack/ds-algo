package cci.ll

import ds.SLLNode
import ds.getNodeAt

fun SLLNode.getKthToLast(k: Int): SLLNode? {
  val kthNodeFromStart = getNodeAt(k)
  var ptr = this
  return kthNodeFromStart?.let {
    var kPtr = it
    while (kPtr.next != null) {
      ptr = ptr.next!!
      kPtr = kPtr.next!!
    }
    ptr
  }
}
