package leetcode.dp

/** https://leetcode.com/problems/russian-doll-envelopes/ */
fun maxEnvelops(envelopes: Array<IntArray>): Int {
  if (envelopes.isEmpty()) {
    return 0
  }
  val sortedByWidth =
    envelopes.map { it[0] to it[1] }.sortedWith(compareBy({ it.first }, { it.second }))

  // After above sorting, this turns into Longest Increasing subsequence problem.
  return sortedByWidth.indices
    .fold(IntArray(envelopes.size)) { table, i ->
      table.apply { // Just the regular dp in FP style.
        val lisUntilCur =
          (0 until i)
            .filter { sortedByWidth[it] canFitInside sortedByWidth[i] }
            .maxOf { validIndex -> this[validIndex] }
        this[i] = lisUntilCur + 1
      }
    }
    .maxOrNull() ?: 1
}

private infix fun Pair<Int, Int>.canFitInside(pair: Pair<Int, Int>) =
  first < pair.first && second < pair.second
