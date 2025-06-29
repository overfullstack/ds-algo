/* gakshintala created on 1/26/20 */
package leetcode.array

import java.util.PriorityQueue

/** https://leetcode.com/problems/top-k-frequent-words/ */
private val COMPARATOR =
  Comparator.comparingInt<Map.Entry<String, Int>> { it.value }
    .thenComparator { a, b -> b.key.compareTo(a.key) }

// Observe `b` comes before `a`, if "a" and "b" are with the same frequency,
// As the requirement is to retain word with lower alphabetical order, in case of clash,
// So we do descend sorting, i.e., push `b` towards head, so it is more prone to `poll()`

fun topKFrequent(words: Array<String>, k: Int): List<String> {
  val wordToFrequency = words.groupingBy { it }.eachCount()
  // Prepare frequency map.
  val minHeap = PriorityQueue(COMPARATOR) // `PriorityQueue` is by default ascending order.
  for (entry in wordToFrequency.entries) {
    // `add()` before `poll()`, as `poll()` doesn't necessarily remove the last added,
    // but least frequent
    minHeap.add(entry)
    if (minHeap.size > k) {
      minHeap.poll() // `poll()` removes the heap head.
    }
  }
  return minHeap.takeWhile { minHeap.isNotEmpty() }.map { minHeap.poll().key }.reversed()
}
