package leetcode.dp

fun maxEnvelops(envelopes: Array<IntArray>): Int {
    if (envelopes.isEmpty()) {
        return 0
    }
    val sortedByWidth = envelopes.map { Pair(it[0], it[1]) }.sortedWith(compareBy({ it.first }, { it.second }))
    return sortedByWidth.indices.fold(IntArray(envelopes.size) { 1 }) { table, i ->
        table.also {
            it[i] += ((0 until i).filter { sortedByWidth[it] canFitInside sortedByWidth[i] }
            .map { valid -> it[valid] }
            .max() ?: 0)
        }
    }.max() ?: 1
}

private infix fun Pair<Int, Int>.canFitInside(pair: Pair<Int, Int>) = first < pair.first && second < pair.second
