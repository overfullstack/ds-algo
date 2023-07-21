package cci.ll

import ga.overfullstack.ds.SLLNode

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
