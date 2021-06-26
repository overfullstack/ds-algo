package leetcode.arrays

import java.util.LinkedList

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * (height, no.of people in-front)
 */
fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
  // Descending order of heights and ascending order of no.of people in-front.
  people.sortWith(compareByDescending<IntArray> { it[0] }.thenBy { it[1] })
  val result = LinkedList<IntArray>()
  for (person in people) {
    // No.of people in-front becomes the index to insert.
    // Since shorter people come later in the order, they push taller people in the same index
    // 🕶 Multiple queues, a queue per k
    result.add(person[1], person)
  }
  return result.toTypedArray()
}
