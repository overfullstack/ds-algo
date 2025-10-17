package educative.ll

import ds.ll.ListNode

fun reorderList(head: ListNode): ListNode {
  var middle: ListNode? = head.middle()
  middle = middle?.reverse()
  var mHead: ListNode? = head
  while (middle?.next != null) {
    val temp = mHead?.next
    mHead?.next = middle
    middle = middle.next // Move middle before mutating its next below
    mHead?.next?.next = temp
    mHead = temp
  }
  return head
}

fun main() {
  println(reorderList(ListNode.of(intArrayOf(10, 20, -22, 21, -12))!!))
}
