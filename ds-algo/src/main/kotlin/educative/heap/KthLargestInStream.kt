package educative.heap

import java.util.PriorityQueue

fun kthLargestInStream(stream: IntArray, k: Int): Int {
  val maxHeap = PriorityQueue<Int>()
  for (i in stream) {
    maxHeap.add(i)
    if (maxHeap.size > k) {
      maxHeap.poll()
    }
  }
  return maxHeap.peek()
}
