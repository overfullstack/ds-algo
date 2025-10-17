package educative.ll

import ds.ll.ListNode
import ds.ll.getNodeAtOrNull

/* 27 Jul 2025 18:15 */

fun ListNode.removeKthToLast(k: Int): ListNode =
  getNodeAtOrNull(k + 1).let { // `+1` as we need a node before the node to be removed
    if (it == null) {
      return this.next!!
    }
    var ptr = this
    var kPtr: ListNode? = it
    while (kPtr?.next != null) {
      kPtr = kPtr.next
      ptr = ptr.next!!
    }
    ptr.next = ptr.next?.next
    this
  }

fun main() {
  val head = ListNode.of(intArrayOf(69, 8, 49, 106, 116, 112))
  head!!.removeKthToLast(6)
  println(head.toString())
}
