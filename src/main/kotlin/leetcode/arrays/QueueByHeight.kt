package leetcode.arrays

import java.util.*

fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
    // Descending order of heights and ascending order of no.of people in-front.
    people.sortWith(compareByDescending<IntArray> { it[0] }.thenBy { it[1] })
    val result = LinkedList<IntArray>()
    for (person in people) {
        result.add(person[1], person) // Shorter people are inserted in the middle as per their no.of people in-front.
    }
    return result.toTypedArray()
}
