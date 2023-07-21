package cci.ll

import ga.overfullstack.ds.SLLNode
import ga.overfullstack.ds.getNodeAt

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
