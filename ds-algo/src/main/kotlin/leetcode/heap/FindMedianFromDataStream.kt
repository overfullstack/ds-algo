package leetcode.heap

import java.util.PriorityQueue

/* 12 Aug 2025 07:47 */

class MedianFinder() {
  val maxHeapForLeft = PriorityQueue(compareByDescending<Int> { it })
  val minHeapForRight = PriorityQueue<Int>()

  fun addNum(num: Int) {
    when {
      maxHeapForLeft.isEmpty() || num <= maxHeapForLeft.peek() -> maxHeapForLeft.add(num)
      else -> minHeapForRight.add(num)
    }
    // ! Favor left-side to have one element more than right-side, when count is odd
    // ! so we can reliably use it for median
    when {
      maxHeapForLeft.size > minHeapForRight.size + 1 -> minHeapForRight.add(maxHeapForLeft.poll())
      minHeapForRight.size > maxHeapForLeft.size -> maxHeapForLeft.add(minHeapForRight.poll())
    }
  }

  fun findMedian(): Double =
    when {
      maxHeapForLeft.size == minHeapForRight.size ->
        maxHeapForLeft.peek() / 2.0 + minHeapForRight.peek() / 2.0
      else -> maxHeapForLeft.peek().toDouble()
    }
}
