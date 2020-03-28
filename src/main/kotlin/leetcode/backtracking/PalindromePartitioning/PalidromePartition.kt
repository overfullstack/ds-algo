/* gakshintala created on 9/23/19 */
package leetcode.backtracking.PalindromePartitioning


fun palindromePartition(
    str: String,
    palindromes: List<String> = emptyList(),
    startIndex: Int = 0
): List<List<String>> {
    if (startIndex > str.lastIndex) { // it comes here only after passing `if(isPalindrome)`
        return listOf(palindromes)
    }
    // Two paths split when palindrome found, marching ahead to find bigger palindromes, branch out to find fresh palindromes after that index.
    var subStr = ""
    return (startIndex..str.lastIndex).fold(emptyList()) { results, index ->
        subStr += str[index] // Appending in loop takes care of marching ahead.
        // If palindrome found, this `subStr` is recorded by appending it to palindromes list and branch out from here to find palindrome freshly starting after this `index` i.e `index + 1`
        if (isPalindrome(subStr)) {
            results + palindromePartition(
                str,
                palindromes + subStr,
                index + 1
            ) // `palindromes + subStr` accumulates the results.
        } else {
            results
        }
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
