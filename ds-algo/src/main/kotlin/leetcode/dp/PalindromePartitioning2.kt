/* gakshintala created on 9/24/19 */
package leetcode.dp

/** https://leetcode.com/problems/palindrome-partitioning-ii/ */
fun minCutsForPalindromePartition(s: String): Int {
  val cutsTable = Array(s.length) { IntArray(s.length) }
  val isPalTable = Array(s.length) { BooleanArray(s.length) }

  // ! For problems with `gap`, especially Palindrome related problems, always initiate table's
  // diagonal.
  for (index in cutsTable.indices) {
    cutsTable[index][index] = 0 // No cuts required for single letter
    isPalTable[index][index] = true // Every single letter is a palindrome
  }

  for (gap in 1..s.lastIndex) {
    for ((i, j) in (gap..s.lastIndex).withIndex()) {
      isPalTable[i][j] =
        // ! This helps in not calculating cuts when it's already a palindrome
        if (gap == 1) s[i] == s[j] else s[i] == s[j] && isPalTable[i + 1][j - 1]
      // This stores no.of cuts required for substring(i..j).
      // If all are distinct characters, it's equal to length of the substring.
      cutsTable[i][j] =
        if (isPalTable[i][j]) 0
        // +1 is the extra cut required for partitioning two palindromes from `i-partition` and
        // `partition-j`
        // If no substring is a palindrome, this +1 makes the result = no.of chars in the string.
        else (i until j).map { cutsTable[i][it] + 1 + cutsTable[it + 1][j] }.minOrNull() ?: 0
    }
  }
  return cutsTable[0][s.lastIndex] // For the entire string
}

fun main() {
  print(minCutsForPalindromePartition(readln()))
}
