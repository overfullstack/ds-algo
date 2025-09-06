package leetcode.backtracking

/* gakshintala created on 12/29/19 */

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */

val DICTIONARY = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

fun letterCombinations(digits: String): List<String> {
  if (digits.isEmpty()) {
    return emptyList()
  }
  return letterCombinationsUtil(digits)
}

private fun letterCombinationsUtil(
  digits: String,
  combination: String = "",
  digitIndex: Int = 0,
): List<String> =
  when (combination.length) {
    digits.length -> listOf(combination)
    // * Every letter in the current word starts a branch with next word letters.
    else ->
      DICTIONARY[digits[digitIndex] - '0'].flatMap {
        letterCombinationsUtil(digits, combination + it, digitIndex + 1)
      }
  }
