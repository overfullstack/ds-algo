package leetcode.dp

/* 11 Aug 2025 10:55 */

fun minDistance(word1: String, word2: String): Int {
  if (word1.isEmpty() and word2.isEmpty()) return 0
  val table = Array(word1.length + 1) { IntArray(word2.length + 1) }
  for (word1Idx in table.indices) {
    table[word1Idx][0] = word1Idx
  }
  for (word2Idx in table.first().indices) {
    table[0][word2Idx] = word2Idx
  }
  for (word1Idx in 1..word1.lastIndex + 1) {
    for (word2Idx in 1..word2.lastIndex + 1) {
      table[word1Idx][word2Idx] =
        when {
          word1[word1Idx - 1] == word2[word2Idx - 1] -> table[word1Idx - 1][word2Idx - 1]
          else ->
            1 + // ! We aren't actually deleting or inserting, but recording the extra operations
              minOf(
                // Delete from word1, so it should take from a shorter word1 value, 
                // until which both words match
                table[word1Idx - 1][word2Idx],
                // Insert into word1, so it should take from a shorter word2 value, 
                // until which both words match
                table[word1Idx][word2Idx - 1],
                table[word1Idx - 1][word2Idx - 1], // Replace in word1
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
