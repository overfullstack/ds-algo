/* gakshintala created on 12/29/19 */
package leetcode.backtracking

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
    digitIndex: Int = 0
): List<String> =
    if (combination.length == digits.length) {
        listOf(combination)
    } else {
        // * Every letter in current word starts a branch with next word letters.
        val lettersOnButton = DICTIONARY[digits[digitIndex] - '0']
        lettersOnButton.flatMap {
            letterCombinationsUtil(digits, combination + it, digitIndex + 1)
        }
    }
