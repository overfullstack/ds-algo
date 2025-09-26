package educative.heap

import java.util.PriorityQueue

/** [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements) */
fun topKFrequent(nums: IntArray, k: Int): IntArray =
  topKFrequent(nums.toTypedArray(), k).toIntArray()

fun topKFrequent(arr: Array<Int>, k: Int): List<Int> {
  val minHeap = PriorityQueue(compareBy<Map.Entry<Int, Int>> { it.value })
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

fun main() {
  println(topKFrequent(arrayOf(1, 1, 1, 2, 2, 3), 2)) // [1, 2]
  println(topKFrequent(arrayOf(1), 1)) // [1]
  println(topKFrequent(arrayOf(4, 1, 2, 2, 3, 1), 2)) // [1, 2]
}
