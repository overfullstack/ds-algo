package cci.ll

import ds.ll.SLLNode

fun SLLNode.detectLoop(): SLLNode? {
  val meetingPtr = getMeetingPtr(this) { it.next?.next }
  return getMeetingPtr(meetingPtr)
}

fun SLLNode.getMeetingPtr(
  that: SLLNode?,
  nextPtrFn: (SLLNode) -> SLLNode? = { it.next }
): SLLNode? =
  when {
    that == null -> null
    value == that.value -> this
    else -> next?.getMeetingPtr(nextPtrFn(that))
  }
