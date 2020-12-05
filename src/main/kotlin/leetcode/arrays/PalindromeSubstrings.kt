package leetcode.arrays

fun countSubstrings(s: String): Int =
    s.indices.map { strechWindow(s, it, it) + strechWindow(s, it, it + 1) }.sum()

private fun strechWindow(s: String, start: Int, end: Int): Int {
    var count = 0
    var startIndex = start
    var endIndex = end
    while (startIndex >= 0 && endIndex <= s.lastIndex && s[startIndex] == s[endIndex]) {
        count++
        startIndex--
        endIndex++
    }
    return count
}
