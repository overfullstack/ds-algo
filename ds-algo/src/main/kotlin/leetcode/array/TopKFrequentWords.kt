/* gakshintala created on 1/26/20 */
package leetcode.array

import java.util.PriorityQueue

/** https://leetcode.com/problems/top-k-frequent-words/ */
fun topKFrequent(words: Array<String>, k: Int): List<String> {
  val wordToFrequency = words.groupingBy { it }.eachCount()
  // `PriorityQueue` is by default ascending sorted.
  // ! Observe for same frequency we do descending sort for key
  // As the requirement is to retain word with lower alphabetical order, in case of clash
  // Making higher alphabetical word more prone to `poll()`
  val minHeap =
    PriorityQueue(compareBy<Map.Entry<String, Int>> { it.value }.thenByDescending { it.key })
  for (entry in wordToFrequency.entries) {
    minHeap.add(entry)
    if (minHeap.size > k) {
      minHeap.poll()
    }
  }
  return minHeap.takeWhile { minHeap.isNotEmpty() }.map { minHeap.poll().key }.reversed()
}
