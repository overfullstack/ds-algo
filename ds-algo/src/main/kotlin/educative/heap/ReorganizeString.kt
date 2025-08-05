package educative.heap

import java.util.AbstractMap.SimpleEntry
import java.util.PriorityQueue

fun reorganizeString(str: String): String {
  val charFreq = str.groupingBy { it }.eachCount()
  val maxHeap = PriorityQueue(compareBy<Map.Entry<Char, Int>> { it.value })
  maxHeap.addAll(charFreq.entries)

  var prev: Map.Entry<Char, Int>? = null
  var result = ""
  // * Exhaust the top frequent characters next to each other
  while (maxHeap.isNotEmpty() || prev != null) {
    if (prev != null && maxHeap.isEmpty()) {
      return ""
    }
    val curTop = maxHeap.poll()
    result += curTop.key
    if (prev != null) {
      maxHeap.add(prev)
      prev = null
    }
    // Add curTop as prev, with decremented frequency
    if (curTop.value - 1 != 0) {
      prev = SimpleEntry(curTop.key, curTop.value - 1)
    }
  }
  return result
}
