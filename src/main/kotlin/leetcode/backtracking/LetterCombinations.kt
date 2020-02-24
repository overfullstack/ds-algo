/* gakshintala created on 12/29/19 */
package leetcode.backtracking

val DICTIONARY = arrayOf("", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")

fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) {
        return emptyList()
    }
    return letterCombinationsUtil(digits)
}

fun letterCombinationsUtil(digits: String, combination: String = "", digitIndex: Int = 0): List<String> {
    if (combination.length == digits.length) {
        return listOf(combination)
    }
    return DICTIONARY[digits[digitIndex] - '0'].fold(emptyList()) { results, char ->
        results + letterCombinationsUtil(digits, combination + char, digitIndex + 1)
    }
}
