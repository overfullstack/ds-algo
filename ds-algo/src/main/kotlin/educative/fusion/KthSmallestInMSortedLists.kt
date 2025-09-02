package educative.fusion

import java.util.PriorityQueue

/* 06 Aug 2025 08:44 */

fun kSmallestNumber(lists: List<List<Int>>, k: Int): Int {
  val minHeap = PriorityQueue(compareBy<Triple<Int, Int, Int>> { it.third })
  for ((row, list) in lists.withIndex()) {
    if (list.isNotEmpty()) {
      minHeap.add(Triple(row, 0, list.first()))
    }
  }
  var k1 = 0
  var kthSmallest = 0
  while (minHeap.isNotEmpty()) {
    val (row, col, value) = minHeap.poll()
    k1++
    if (k1 == k) return value
    // ! Coz the problem says If k is greater than the total number of elements in the input lists,
    // return the greatest element from all the lists, and if there are no elements in the input
    // lists, return 0.
    kthSmallest = value
    if (col + 1 <= lists[row].lastIndex) {
      minHeap.add(Triple(row, col + 1, lists[row][col + 1]))
    }
  }
  return kthSmallest
}
