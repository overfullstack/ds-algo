package leetcode.greedy

/**
 * https://leetcode.com/problems/partition-labels/
 */
fun partitionLabels(S: String): List<Int> {
    val charToLastIndex = S.indices.associateBy { S[it] }
    var maxLast = 0
    var start = 0
    return S.indices
        .onEach { maxLast = maxOf(maxLast, charToLastIndex[S[it]]!!) }
        .filter { maxLast == it }
        .map { partition -> (partition - start + 1).also { start = partition + 1 } }
}

fun main() {
    partitionLabels("partitionLabels")
}
