/* gakshintala created on 8/23/19 */
package stacks

import java.util.*

fun nextHighest(h: IntArray) {
    val stk = ArrayDeque<Int>()
    val eleToNextHighestMap = mutableMapOf<Int, Int>()
    stk.push(h[0])
    for (i in 1 until h.size) {
        while (!stk.isEmpty() && stk.peek() < h[i]) {
            eleToNextHighestMap[stk.pop()] = h[i]
        }
        stk.push(h[i])
    }
    if (!stk.isEmpty()) {
        eleToNextHighestMap[stk.pop()] = -1
    }
    eleToNextHighestMap.forEach { (key, value) -> println("$key $value") }
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        nextHighest(arr)
    }
}