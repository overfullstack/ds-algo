/* gakshintala created on 9/23/19 */
package leetcode.backtracking

/** [131. Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning/) */
fun palindromePartition(
  str: String,
  palindromes: List<String> = emptyList(),
  startIndex: Int = 0,
): List<List<String>> =
  if (startIndex > str.lastIndex) {
    listOf(palindromes)
  } else {
    (startIndex..str.lastIndex)
      .asSequence()
      .map { it to str.substring(startIndex..it) }
      .filter { (_, substr) -> isPalindrome(substr) }
      .flatMap { (idx, substr) -> palindromePartition(str, palindromes + substr, idx + 1) }
      .toList()
  }

fun isPalindrome(str: String): Boolean = str == str.reversed()

fun main() {
  palindromePartition(readln()).forEach { result ->
    result.forEach { print("$it ") }
    println()
  }
}
