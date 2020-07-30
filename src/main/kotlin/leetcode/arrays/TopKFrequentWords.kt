/* gakshintala created on 1/26/20 */
package leetcode.arrays

import java.util.*

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 */
private val COMPARATOR =
    Comparator.comparingInt<Map.Entry<String, Int>> { it.value }
        .thenComparator { a, b -> b.key.compareTo(a.key) }
// Observe `b` comes before `a`, if "a" and "b" are with same frequency,
// As the requirement is to retain word with lower alphabetical order, in case of clash,
// So we do descending sorting, i.e., push `b` towards head so it is more prone to `poll()`

fun topKFrequent(words: Array<String>, k: Int): List<String> {
    val wordToFrequency = mutableMapOf<String, Int>()
    // Prepare frequency map.
    for (word in words) {
        wordToFrequency.merge(word, 1) { oldValue, _ -> oldValue.inc() }
    }
    val minHeap = PriorityQueue(COMPARATOR) // `PriorityQueue` is by default ascending order.
    for (entry in wordToFrequency.entries) {
        minHeap.add(entry) // `add()` before `poll()`, as `poll()` doesn't necessarily remove the last added, but least frequent
        if (minHeap.size > k) {
            minHeap.poll() // `poll()` removes the heap head.
        }
    }
    return minHeap.takeWhile { minHeap.isNotEmpty() }.map { minHeap.poll().key }.reversed()
}
