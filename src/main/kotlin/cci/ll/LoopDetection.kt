package cci.ll

import ds.SLLNode

private val logger = mu.KotlinLogging.logger {}

fun SLLNode.detectLoop(): SLLNode? {
    val meetingPtr = getMeetingPtr(this) { it.next?.next }
    logger.info { meetingPtr }
    return getMeetingPtr(meetingPtr)
}

tailrec fun SLLNode.getMeetingPtr(that: SLLNode?, nextPtrFn: (SLLNode) -> SLLNode? = { it.next }): SLLNode? =
    when {
        that == null -> null
        value == that.value -> this
        else -> next?.getMeetingPtr(nextPtrFn(that))
    }