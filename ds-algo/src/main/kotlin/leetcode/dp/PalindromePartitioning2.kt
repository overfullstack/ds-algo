/* gakshintala created on 9/24/19 */
package leetcode.dp

/** [132. Palindrome Partitioning II](https://leetcode.com/problems/palindrome-partitioning-ii/) */
fun minCut(s: String): Int {
  val cutsTable = Array(s.length) { IntArray(s.length) }
  val isPalTable = Array(s.length) { BooleanArray(s.length) }

  // ! For problems with `windowLen`, especially Palindrome related problems, 
  // always initiate table's diagonal.
  for (index in cutsTable.indices) {
    cutsTable[index][index] = 0 // No cuts required for single letter
    isPalTable[index][index] = true // Every single letter is a palindrome
  }

  for (windowLen in 1..s.lastIndex) {
    for ((i, j) in (windowLen..s.lastIndex).withIndex()) {
      isPalTable[i][j] =
        // ! This helps in not calculating cuts when it's already a palindrome
        if (windowLen == 1) s[i] == s[j] else (s[i] == s[j] && isPalTable[i + 1][j - 1])
      // This stores no.of cuts required for substring(i..j).
      // If all are distinct characters, it's equal to length of the substring.
      cutsTable[i][j] =
        if (isPalTable[i][j]) 0
        // +1 is the extra cut required for partitioning two palindromes from `i..partition` and
        // `partition+1..j`
        // If no substring is a palindrome, this +1 makes the result = no.of chars in the string.
        // `j` is excluded as we do `it + 1` for partition
        else (i until j).minOfOrNull { cutsTable[i][it] + 1 + cutsTable[it + 1][j] } ?: 0
    }
  }
  return cutsTable[0][s.lastIndex] // For the entire string
}

fun main() {
  print(minCut(readln()))
}
