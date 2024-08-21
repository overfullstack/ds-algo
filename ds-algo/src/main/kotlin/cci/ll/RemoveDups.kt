package cci.ll

import ds.ll.SLLNode

fun SLLNode?.removeDupsFromSLL() {
  var current = this
  while (current?.next != null) {
    var runner = current
    while (runner?.next != null) {
      if (current.value == runner.next?.value) {
        runner.next = runner.next?.next
      } else {
        runner = runner.next
      }
    }
    current = current.next
  }
}
