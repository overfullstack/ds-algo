package leetcode.dp

/** [354. Russian Doll Envelopes](https://leetcode.com/problems/russian-doll-envelopes/) */
fun maxEnvelops(envelopes: Array<IntArray>): Int {
  if (envelopes.isEmpty()) {
    return 0
  }
  val sortedByWidth =
    envelopes.map { it[0] to it[1] }.sortedWith(compareBy({ it.first }, { it.second }))

  // After above sorting, this turns into Longest Increasing subsequence problem.
  val table = IntArray(envelopes.size)
  table[0] = 1
  for (i in 1..sortedByWidth.lastIndex) {
    for (j in 0 until i) {
      if (sortedByWidth[j] canFitInside sortedByWidth[i]) {
        table[i] = maxOf(table[i], table[j] + 1)
      }
    }
  }
  return table.max()
}

private infix fun Pair<Int, Int>.canFitInside(pair: Pair<Int, Int>) =
  first < pair.first && second < pair.second
