/* gakshintala created on 12/26/19 */
package leetcode.stack.StockSpan

import java.util.*

class StockSpan {
    private val stk = Stack<Pair<Int, Int>>()

    fun next(price: Int): Int {
        var span = 0 // made 0 every time
        while (stk.isNotEmpty() && price >= stk.peek().first) {
            span += stk.pop().second
        }
        stk.push(price to span + 1)
        return span + 1
    }
}

fun main() {
    val stockSpan = StockSpan()
    readLine()!!.split(",").map { it.trim().toInt() }.forEach { println(stockSpan.next(it)) }
}
