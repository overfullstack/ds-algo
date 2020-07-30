/* gakshintala created on 8/28/19 */
package hackerrank

import java.util.*

fun riddle(arr: Array<Long>): Array<Long> {
    val minIndexOnRight = IntArray(arr.size) { arr.size }
    val minIndexOnLeft = IntArray(arr.size) { -1 }
    val stk = ArrayDeque<Int>()
    stk.push(0)
    for (i in 1 until arr.size) {
        while (!stk.isEmpty() && arr[i] < arr[stk.peek()]) {
            minIndexOnRight[stk.pop()] = i
        }
        stk.push(i)
    }

    stk.clear()
    stk.push(arr.lastIndex)

    for (i in arr.size - 2 downTo 0) {
        while (!stk.isEmpty() && arr[i] < arr[stk.peek()]) {
            minIndexOnLeft[stk.pop()] = i
        }
        stk.push(i)
    }

    val results = LongArray(arr.size + 1)
    IntArray(arr.size) { i -> minIndexOnRight[i] - minIndexOnLeft[i] - 1 }
        .forEachIndexed { index, window -> results[window] = maxOf(results[window], arr[index]) }

    for (i in results.size - 2 downTo 1) {
        results[i] = maxOf(results[i], results[i + 1])
    }

    return results.drop(1).toTypedArray()
}

fun main() {
    val scan = Scanner(System.`in`)

    val arr = scan.nextLine().split(" ").map { it.trimStart().trim().toLong() }.toTypedArray()

    val res = riddle(arr)

    println(res.joinToString(" "))
}