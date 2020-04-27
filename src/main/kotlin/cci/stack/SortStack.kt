package cci.stack

import java.util.Stack

fun Stack<Int>.sortWithStack(): Stack<Int> {
    val bufferStk = Stack<Int>()
    while (isNotEmpty()) {
        val top = pop()
        while (bufferStk.isNotEmpty() && top > bufferStk.peek()) {
            push(bufferStk.pop())
        }
        bufferStk.push(top)
    }
    return bufferStk
}
