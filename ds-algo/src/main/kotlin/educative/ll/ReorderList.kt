package educative.ll

import ds.ll.SLLNode

fun reorderList(head: SLLNode): SLLNode {
  var middle = head.middle()
  var newMiddle: SLLNode? = middle.reverse()
  var mHead: SLLNode? = head
  while (mHead?.next != newMiddle) {
    val nodeToInsert = newMiddle
    newMiddle = newMiddle?.next
    mHead?.insertNext(nodeToInsert!!)
    mHead = mHead?.next?.next
  }
  return head
}
