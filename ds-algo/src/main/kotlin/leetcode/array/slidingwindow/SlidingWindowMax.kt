/* gakshintala created on 8/25/19 */
package leetcode.array.slidingwindow

/** [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) * */
fun slidingWindowMax(nums: IntArray, k: Int): IntArray {
  if (nums.isEmpty() || k == 0) {
    return IntArray(0)
  }
  val result = mutableListOf<Int>()
  val deque = ArrayDeque<Int>()
  // Deal with the Init window separately outside of loop.
  for (i in 0 until k) {
    while (deque.isNotEmpty() && nums[i] >= nums[deque.last()]) {
      // Imagine this element shattering out lesser/equal elements out from last to first
      // (right to the left direction)
      deque.removeLast()
    }
    deque.add(i) // deque only indexes.
  }
  // The windows are overlapping, so we have a result for each iteration.
  for (i in k..nums.lastIndex) {
    result.add(nums[deque.first()]) // peek holds max for that window.
    // eliminate the ones irrelevant for this window.
    // ! enqueued Indexes instead of values for this reason
    val window = i - k
    while (deque.isNotEmpty() && deque.first() <= window) {
      deque.removeFirst()
    }
    // shatter the smaller ones.
    while (deque.isNotEmpty() && nums[i] >= nums[deque.last()]) {
      deque.removeLast()
    }
    deque.add(i)
  }

  // Add the peek of the remaining dequeue.
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
  val k = readlnOrNull()?.toInt() ?: 0
  // slidingWindowMax(arr, k).forEach { print("$it ") }
  slidingWindowMaxIdiomatic(arr, k).forEach { print("$it ") }
}
