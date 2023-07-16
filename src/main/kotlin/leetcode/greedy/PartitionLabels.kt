package leetcode.greedy

/**
 * https://leetcode.com/problems/partition-labels/ Partition string into as many parts as possible
 * so that each letter appears in at most one part.
 */
fun partitionLabels(S: String): List<Int> {
  val charToLastOccuranceIndex = S.indices.associateBy { S[it] }
  var maxLastOccurance = 0 // * For a bunch of chars in the partition
  var start = 0
  return S.indices
    .onEach { maxLastOccurance = maxOf(maxLastOccurance, charToLastOccuranceIndex[S[it]]!!) }
    // * This portion engulfs a bunch of chars which don't exist in later part of the string, so cut
    // a partition.
    .filter { maxLastOccurance == it }
    .map { partition -> (partition - start + 1).also { start = partition + 1 } }
}

fun main() {
  partitionLabels("partitionLabels")
}
