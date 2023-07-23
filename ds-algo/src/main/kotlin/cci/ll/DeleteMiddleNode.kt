package cci.ll

import ga.overfullstack.ds.ll.SLLNode

tailrec fun SLLNode.deleteMe(): Unit = if (next?.next == null) next = null else next!!.deleteMe()
