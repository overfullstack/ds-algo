/* gakshintala created on 12/8/19 */
package leetcode.stack

import java.util.*

fun smallestSubsequence(text: String): String {
    val charFrequency = text.groupingBy { it }.eachCount().toMutableMap()
    val visited = mutableSetOf<Char>()
    val stk = ArrayDeque<Char>()
    for (ch in text) {
        charFrequency.computeIfPresent(ch) { _, freq -> freq.dec() }
        if (!visited.contains(ch)) {
            // `stk.peek() > ch` is for lexicographical reason. If a `stk.peek()` is greater than current char and has positive frequency,
            // we are gonna encounter it again later, so remove it for now and push the smaller ch.
            while (stk.isNotEmpty() && stk.peek() > ch && charFrequency.getOrDefault(stk.peek(), 0) > 0) {
                visited.remove(stk.pop())
            }
            stk.push(ch)
            visited.add(ch)
        }
    }

    return stk.reversed().joinToString("")
}

fun main() {
    println(smallestSubsequence(readLine()!!))
}