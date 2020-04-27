package cci.ll

import ds.SLLNode

fun SLLNode.deleteMe() {
    value = next!!.value
    if (next?.next != null) {
        next!!.deleteMe()
    } else {
        next = null
    }
}