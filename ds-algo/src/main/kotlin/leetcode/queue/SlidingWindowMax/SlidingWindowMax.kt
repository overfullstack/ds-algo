/* gakshintala created on 8/25/19 */
package leetcode.queue.SlidingWindowMax

fun slidingWindowMax(nums: IntArray, k: Int): IntArray {
  if (nums.isEmpty() || k == 0) {
    return IntArray(0)
  }
  val result = mutableListOf<Int>()
  val deque = ArrayDeque<Int>()
  // Deal with the Init window separately outside of loop.
  for (i in 0 until k) {
    while (deque.isNotEmpty() && nums[i] >= nums[deque.last()]) {
      // Imagine this element shattering out lesser/equal elements out from last to first (right to
      // left direction)
      deque.removeLast()
    }
    deque.add(i) // deque only indexes.
  }
  // The windows are overlapping, so we have a result for each iteration.
  for (i in k..nums.lastIndex) {
    result.add(nums[deque.first()]) // peek holds max for that window.
    // eliminate the ones irrelevant for this window.
    while (deque.isNotEmpty() && deque.first() <= i - k) {
      deque.removeFirst()
    }
    // shatter the smaller ones.
    while (deque.isNotEmpty() && nums[i] >= nums[deque.last()]) {
      deque.removeLast()
    }
    deque.add(i)
  }

  // Just add the peek of remaining dequeue.
  result.add(nums[deque.first()])
  return result.toIntArray()
}

fun slidingWindowMaxIdiomatic(nums: IntArray, k: Int): IntArray {
  if (nums.isEmpty() || k == 0) {
    return IntArray(0)
  }
  val maxResultIterator = nums.asSequence().windowed(k).map { it.maxOrNull() }.iterator()
  val maxResultSize = nums.size - k + 1
  return IntArray(maxResultSize) { maxResultIterator.next()!! }
}

fun main() {
  val arr = readln().split(",").map { it.toInt() }.toIntArray()
  val k = readLine()?.toInt() ?: 0
  // slidingWindowMax(arr, k).forEach { print("$it ") }
  slidingWindowMaxIdiomatic(arr, k).forEach { print("$it ") }
}
