package leetcode.greedy

/**
 * https://leetcode.com/problems/partition-labels/ Partition string into as many parts as possible
 * so that each letter appears in at most one part.
 */
fun partitionLabels(s: String): List<Int> {
  val charToLastOccurrenceIndex = s.indices.associateBy { s[it] }
  var maxLastOccurrence = 0 // * For a bunch of chars in the partition
  var start = 0
  return s.indices
    .asSequence()
    .onEach { maxLastOccurrence = maxOf(maxLastOccurrence, charToLastOccurrenceIndex[s[it]]!!) }
    // * This portion engulfs a bunch of chars which don't exist in later part of the string, so cut
    // a partition.
    .filter { maxLastOccurrence == it }
    .map { partition -> (partition - start + 1).also { start = partition + 1 } }
    .toList()
}

fun main() {
  println(partitionLabels("partitionLabels"))
}
