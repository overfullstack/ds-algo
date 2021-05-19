/* gakshintala created on 12/8/19 */
package leetcode.dp.InterleavingStrings

fun interLeavingStrings(str1: String, str2: String, str3: String): Boolean {
    if (str3.length != str1.length + str2.length) {
        return false
    }
    val table = BooleanArray(str2.length + 1)

    table[0] = true
    for (i in 1..table.lastIndex) { // Is `str3` made of only `str1`
        table[0] =
            table[0] && str3[i - 1] == str1[i - 1] // Analogous to first column in 1D, accumulating to index `0`
    }
    for (j in 1..table.lastIndex) {
        table[j] = table[j - 1] && str3[j - 1] == str2[j - 1]
    }

    // Table indices run direclty on `i,j`. Corresponding String indices run with `-1` difference
    for (i in 1..str1.length) {
        for (j in 1..str2.length) {
            // Excluding char from str2, If u imagine a 2D array, `table[j]` indicates we are in the same column and previous row.
            // * `table[j]` here represents previous results with `i-1` chars from `str1` and `j` chars from `str2`
            val isCharFromStr1 =
                table[j] && (str3[i + j - 1] == str1[i - 1]) // -1 as 0 is taken for empty str1, str2
            val isCharFromStr2 = table[j - 1] && (str3[i + j - 1] == str2[j - 1])
            table[j] = isCharFromStr2 || isCharFromStr1
        }
    }
    return table[str2.length]
}

fun main() {
    println(interLeavingStrings(readLine()!!, readLine()!!, readLine()!!))
}
