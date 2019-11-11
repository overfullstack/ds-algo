/* gakshintala created on 9/12/19 */
package leetcode.MinWindowSubString

fun main() {
    val str = readLine()!!
    val pattern = readLine()!!
    print(minWindow(str, pattern))
}

fun minWindow(str: String, pattern: String): String {
    if (pattern.length > str.length) {
        return ""
    }

    val patternLetterFreq = pattern.groupingBy { it }.eachCount()
    val strLetterFreq = mutableMapOf<Char, Int>()
    var matchCount = 0
    var startIndex = 0
    var windowStartIndex = -1
    var minWindowLength = Int.MAX_VALUE
    for ((strIndex, letter) in str.withIndex()) {
        val letterFreqInStr = strLetterFreq.merge(letter, 1, Int::plus)
        if (letterFreqInStr!! <= (patternLetterFreq[letter] ?: 0)) {
            matchCount++
        }
        // Once matchCount meets patternLength, it always goes inside this if condition. No one touches matchCount again.
        if (matchCount == pattern.length) {
            // Compress from start by eliminating irrelevant characters or characters with extra frequency
            // Then parent loop takes care of extending on the right side.
            while (!patternLetterFreq.containsKey(str[startIndex]) || (strLetterFreq[str[startIndex]]!! > patternLetterFreq[str[startIndex]] ?: 0)) {
                strLetterFreq.computeIfPresent(str[startIndex]) { _, value -> value.dec() }
                startIndex++
            }
            val curWindowLength = strIndex - startIndex + 1
            if (curWindowLength < minWindowLength) {
                windowStartIndex = startIndex
                minWindowLength = curWindowLength
            }
        }
    }
    return if (windowStartIndex == -1) "" else str.substring(windowStartIndex, windowStartIndex + minWindowLength)
    
}