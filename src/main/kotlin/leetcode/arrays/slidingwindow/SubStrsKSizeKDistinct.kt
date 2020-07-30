package leetcode.arrays.slidingwindow

fun subStringsWithKSizeKDistinct(s: String, k: Int): List<String> {
    var start = 0
    val window = mutableSetOf<Char>()
    val result = mutableListOf<String>()
    for ((index, char) in s.withIndex()) {
        // We should strictly have unique character, no place for duplicates anywhere in the window.
        // Shrink window from start till lastOccurance of char.
        while (window.contains(char)) {
            window.remove(s[start])
            start++
        }

        window.add(char)

        if (window.size == k) {
            result.add(s.substring(start..index))
            window.remove(s[start]) // Removing start as and when the window is recorded.
            start++
        }
    }
    return result
}

fun main() {
    println(subStringsWithKSizeKDistinct("abcabc", 3))
}