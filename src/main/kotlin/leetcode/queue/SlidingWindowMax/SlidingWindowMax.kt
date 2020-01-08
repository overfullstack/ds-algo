/* gakshintala created on 8/25/19 */
package leetcode.queue.SlidingWindowMax

import java.util.*

fun slidingWindowMax(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty() || k == 0) {
        return IntArray(0)
    }
    val result = mutableListOf<Int>()
    val deque = ArrayDeque<Int>()
    // Deal with Init window separately outside of loop.
    for (i in 0 until k) {
        while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
            deque.removeLast() // Imagine this element shattering out lesser/equal elements out from last to first (right to left direction)
        }
        deque.addLast(i) // deque only indexes.
    }
    for (i in k until nums.size) { // The windows are overlapping, so we have a result for each iteration.
        result.add(nums[deque.peekFirst()]) // peek holds max for that window.
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) { // eliminate the ones irrelevant for this window.
            deque.removeFirst()
        }
        while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) { // shatter the smaller ones.
            deque.removeLast()
        }
        deque.addLast(i)
    }

    // Just add the peek of remaining dequeue.
    result.add(nums[deque.peekFirst()])
    return result.toIntArray()
}

fun slidingWindowMaxIdiomatic(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty() || k == 0) {
        return IntArray(0)
    }
    val maxResultIterator = nums.asSequence().windowed(k).map { it.max() }.iterator()
    val maxResultSize = nums.size - k + 1
    return IntArray(maxResultSize) { maxResultIterator.next()!! }
}

fun main() {
    val arr = readLine()!!.split(",").map { it.toInt() }.toIntArray()
    val k = readLine()?.toInt() ?: 0
    //slidingWindowMax(arr, k).forEach { print("$it ") }
    slidingWindowMaxIdiomatic(arr, k).forEach { print("$it ") }
}