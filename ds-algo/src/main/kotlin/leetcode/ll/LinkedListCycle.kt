package leetcode.ll

import ds.ListNode

/* 15 Oct 2025 20:24 */

/** [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/) */
fun hasCycle(head: ListNode?): Boolean {
  var walker = head
  var runner = head
  while (runner != null) {
    walker = walker?.next
    runner = runner.next?.next
    if (runner != null && walker == runner) {
      return true
    }
  }
  return false
}
