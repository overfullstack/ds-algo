package cci.ll

import ds.ll.SLLNode
import ds.ll.getNodeAtOrNull

fun SLLNode.getKthToLast(k: Int): SLLNode? =
  getNodeAtOrNull(k)?.let {
    var ptr = this
    var kPtr: SLLNode? = it
    while (kPtr?.next != null) {
      kPtr = kPtr.next
      ptr = ptr.next!!
    }
    ptr
  }

fun main() {
  println(SLLNode.of(intArrayOf(1, 2, 3, 4))?.getKthToLast(2)?.value)
}
