/* gakshintala created on 9/23/19 */
package leetcode


fun palindromePartition(
    result: MutableList<String>,
    str: String,
    startIndex: Int,
    results: MutableList<MutableList<String>>
): List<List<String>> {
    if (startIndex > str.lastIndex) {
        results.add(result.toMutableList()) // taking copy
        return results
    }
    var subStr = ""
    for (i in startIndex..str.lastIndex) {
        subStr += str[i]
        if (isPalindrome(subStr)) {
            result.add(subStr)
            palindromePartition(result, str, i + 1, results)
            if (result.isNotEmpty()) result.removeAt(result.lastIndex) // removing last and concatenating it in next iteration, to check for more.
        }
    }
    return results
}

fun isPalindrome(str: String): Boolean = str == str.reversed()

fun main() {
    palindromePartition(mutableListOf(), readLine()!!, 0, mutableListOf())
        .forEach { result ->
            result.forEach { print("$it ") }
            println()
        }
}