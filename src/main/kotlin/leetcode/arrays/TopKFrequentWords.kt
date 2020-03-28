/* gakshintala created on 1/26/20 */
package leetcode.arrays

import java.util.*

private val COMPARATOR =
    Comparator.comparingInt<Map.Entry<String, Int>> { it.value }
        .thenComparator { a, b -> b.key.compareTo(a.key) }
// Observe `b` comes before `a`, imagine you have "a" and "b" with same frequency, 
// we push "b" towards head so it is more prone to `poll()` 

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val wordToFrequency = mutableMapOf<String, Int>()
    // Prepare frequency map.
    for (word in words) {
        wordToFrequency.merge(word, 1) { oldValue, _ -> oldValue.inc() }
    }
    val minHeap = PriorityQueue<Map.Entry<String, Int>>(COMPARATOR) // `PriorityQueue` is by default ascending order.
    for (entry in wordToFrequency.entries) {
        minHeap.add(entry) // `add()` before `poll()`, as `poll()` doesn't necessarily remove the last added, but lest frequent
        if (minHeap.size > k) {
            minHeap.poll() // `poll()` removes the heap head.
        }
    }
    return minHeap.takeWhile { minHeap.isNotEmpty() }.map { minHeap.poll().key }.reversed()
}
