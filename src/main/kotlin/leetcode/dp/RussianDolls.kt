package leetcode.dp

fun maxEnvelops(envelopes: Array<IntArray>): Int {
    if (envelopes.isEmpty()) {
        return 0
    }
    val sortedByWidth = envelopes.map { it[0] to it[1] }.sortedWith(compareBy({ it.first }, { it.second }))

    // After above sorting, this turns into Longest Increasing subsequence problem.
    return sortedByWidth.indices.fold(IntArray(envelopes.size) { 1 }) { table, i ->
        table.apply { // Just the regular DP in FP style.
            this[i] += (0 until i)
                .filter { sortedByWidth[it] canFitInside sortedByWidth[i] }
                .map { valid -> this[valid] }
                .maxOrNull() ?: 0
        }
    }.maxOrNull() ?: 1
}

private infix fun Pair<Int, Int>.canFitInside(pair: Pair<Int, Int>) = first < pair.first && second < pair.second
