package cci.ll

import ds.ll.ListNode
import ds.ll.getNodeAtOrNull

fun ListNode.getKthToLast(k: Int): ListNode? =
  getNodeAtOrNull(k)?.let {
    var ptr = this
    var kPtr: ListNode? = it
    while (kPtr?.next != null) {
      kPtr = kPtr.next
      ptr = ptr.next!!
    }
    ptr
  }

fun main() {
  println(ListNode.of(intArrayOf(1, 2, 3, 4))?.getKthToLast(2)?.value)
}
