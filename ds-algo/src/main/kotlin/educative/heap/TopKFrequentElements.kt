package educative.heap

import java.util.PriorityQueue

fun topKFrequent(arr: Array<Int>, k: Int): List<Int> {
  val minHeap = PriorityQueue<Map.Entry<Int, Int>>()
  val freqMap = arr.groupingBy { it }.eachCount()
  freqMap.entries.take(k).forEach { minHeap.add(it) }

  freqMap.entries.drop(k).forEach {
    if (it.value > minHeap.peek().value) {
      minHeap.poll()
      minHeap.add(it)
    }
  }
  return minHeap.toList().sortedBy { it.value }.map { it.key }
}
