package leetcode.dp

/* 11 Aug 2025 10:55 */

/** [72. Edit Distance](https://leetcode.com/problems/edit-distance) */
fun minDistance(word1: String, word2: String): Int {
  if (word1.isEmpty() and word2.isEmpty()) return 0
  // ! `+1` for comparing empty string
  val table = Array(word1.length + 1) { IntArray(word2.length + 1) }
  for (word1Idx in table.indices) {
    table[word1Idx][0] = word1Idx
  }
  for (word2Idx in table.first().indices) {
    table[0][word2Idx] = word2Idx
  }
  // * `table[i][j]` represents edit distance between `word1[0..i-1]` and `word2[0..j-1]`
  for (word1Idx in 1..word1.lastIndex + 1) {
    for (word2Idx in 1..word2.lastIndex + 1) {
      table[word1Idx][word2Idx] =
        when {
          word1[word1Idx - 1] == word2[word2Idx - 1] -> table[word1Idx - 1][word2Idx - 1]
          else ->
            // * Convert word1 -> word 2.
            // ! We aren't actually deleting or inserting, but recording the extra operations
            // * In an iteration while we keep word1 constant and increment word2, we have 3 stages:
            // ! 1. word1 longer than word2, delete from word1 to make word2
            // ! 2. word2 longer than word1, insert into word1 to make word2
            // ! 3. word1 and word2 of same length, replace in word1
            1 +
              minOf(
                table[word1Idx - 1][word2Idx], // 1
                table[word1Idx][word2Idx - 1], // 2
                table[word1Idx - 1][word2Idx - 1], // 3
              )
        }
    }
  }
  return table.last().last()
}

fun main() {
  println(minDistance("horse", "ros"))
  println(minDistance("intention", "execution"))
  println(minDistance("a", ""))
}
