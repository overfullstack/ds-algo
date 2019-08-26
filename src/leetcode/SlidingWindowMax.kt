/* gakshintala created on 8/25/19 */
package leetcode

import java.util.*

fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val result = mutableListOf<Int>()
    val deque = ArrayDeque<Int>()
    for (i in 0 until k) {
        while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
            deque.removeLast()
        }
        deque.addLast(i)
    }
    for (i in k until nums.size) {
        result.add(nums[deque.peekFirst()])
        while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
            deque.removeFirst()
        }
        while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
            deque.removeLast()
        }
        deque.addLast(i)
    }
    if(!deque.isEmpty()) {
        result.add(nums[deque.peekFirst()])
    }
    return result.toIntArray()
}

fun main() {
    val noOfTests = readLine()!!.toInt()
    repeat(noOfTests) {
        val k = readLine()?.toInt() ?: 0
        val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        maxSlidingWindow(arr, k).forEach { print("$it ") }
    }
}