/* gakshintala created on 9/23/19 */
package leetcode.backtracking.PalindromePartitioning

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 */
fun palindromePartition(
  str: String,
  palindromes: List<String> = emptyList(),
  startIndex: Int = 0
): List<List<String>> =
  if (startIndex > str.lastIndex) {
    listOf(palindromes)
  } else {
    (startIndex..str.lastIndex)
      .filter { isPalindrome(str.substring(startIndex..it)) }
      .flatMap {
        palindromePartition(
          str,
          palindromes + str.substring(startIndex..it),
          it + 1
        )
      }
  }

fun isPalindrome(str: String): Boolean = str == str.reversed()

fun main() {
  palindromePartition(
    readLine()!!
  ).forEach { result ->
    result.forEach { print("$it ") }
    println()
  }
}
