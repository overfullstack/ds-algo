package educative.ll

import ds.ll.SLLNode

fun reorderList(head: SLLNode): SLLNode {
  var middle: SLLNode? = head.middle()
  middle = middle?.reverse()
  var mHead: SLLNode? = head
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
  println(reorderList(SLLNode.of(intArrayOf(10, 20, -22, 21, -12))!!))
}
