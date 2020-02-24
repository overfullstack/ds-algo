/* gakshintala created on 12/25/19 */
package hackerrank.greedy

import java.util.*

fun maxMinOptimized(k: Int, arr: IntArray): Int {
    arr.sort()
    return arr.dropLast(k - 1).mapIndexed { i, value -> arr[i + k - 1] - value }.min() ?: 0
}

fun maxMin(k: Int, arr: IntArray): Int {
    arr.sort()
    return arr.asSequence().windowed(k).map { it.max()?.minus((it.min() ?: 0)) ?: 0 }.min() ?: 0
}

fun maxMinWithQueue(k: Int, arr: IntArray): Int {
    if (arr.isEmpty() || k == 0) {
        return 0
    }
    arr.sort()
    val (slidingWindowMaxs, slidingWindowMins) = slidingWindow(arr, k)
    return slidingWindowMaxs.zip(slidingWindowMins) { max, min -> max - min }.min() ?: 0
}

fun slidingWindow(nums: IntArray, k: Int): Pair<IntArray, IntArray> {
    val maxResult = mutableListOf<Int>()
    val minResult = mutableListOf<Int>()
    val maxDeque = ArrayDeque<Int>() // Deque stands for double ended queue.
    val minDeque = ArrayDeque<Int>()
    // Deal with Init window separately outside of loop.
    for (i in 0 until k) {
        enqueueCur(maxDeque, nums, i, fun Int.(b: Int) = this >= b)
        enqueueCur(minDeque, nums, i, fun Int.(b: Int) = this <= b)
    }
    for (i in k until nums.size) { // The windows are overlapping, so we have a result for each iteration.
        maxResult.add(nums[maxDeque.peekFirst()]) // peek holds max for that window.
        minResult.add(nums[minDeque.peekFirst()]) // peek holds max for that window.

        while (!maxDeque.isEmpty() && maxDeque.peekFirst() <= i - k) { // eliminate the ones irrelevant for this window.
            maxDeque.removeFirst()
        }

        enqueueCur(maxDeque, nums, i, fun Int.(b: Int) = this >= b)
        enqueueCur(minDeque, nums, i, fun Int.(b: Int) = this <= b)
    }

    maxResult.add(nums[maxDeque.peekFirst()])
    minResult.add(nums[minDeque.peekFirst()])

    return Pair(maxResult.toIntArray(), minResult.toIntArray())
}

private fun enqueueCur(maxDeque: ArrayDeque<Int>, nums: IntArray, i: Int, comparator: Int.(Int) -> Boolean) {
    while (!maxDeque.isEmpty() && nums[i].comparator(nums[maxDeque.peekLast()])) {
        maxDeque.removeLast() // Imagine this element shattering out lesser/equal elements out from last to first
    }
    maxDeque.addLast(i)
}

fun main() {
    val arrSize = readLine()!!.toInt()
    val k = readLine()!!.toInt()
    print(maxMinOptimized(k, IntArray(arrSize) { readLine()!!.trim().toInt() }))
}
