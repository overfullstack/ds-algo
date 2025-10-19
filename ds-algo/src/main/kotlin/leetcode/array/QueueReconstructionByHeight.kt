package leetcode.array

import java.util.LinkedList
import utils.toIntArray
import utils.toPair

/**
 * [406. Queue Reconstruction by
 * Height](https://leetcode.com/problems/queue-reconstruction-by-height/) (height, number of people
 * in-front)
 */
fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
  // ! Descending order of heights, as we shorter people come later to push taller people to right
  val sortedPeople =
    people
      .map { it.toPair() }
      .sortedWith(compareByDescending<Pair<Int, Int>> { it.first }.thenBy { it.second })
  val result = LinkedList<Pair<Int, Int>>()
  for (person in sortedPeople) {
    // No.of people in-front becomes the index to insert.
    // Since shorter people come later in the order, they push taller people in the same index
    result.add(person.second, person)
  }
  return result.map { it.toIntArray() }.toTypedArray()
}
