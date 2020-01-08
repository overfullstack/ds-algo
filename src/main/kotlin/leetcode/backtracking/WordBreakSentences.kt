/* gakshintala created on 10/2/19 */
package leetcode.backtracking

private fun wordBreak(s: String, wordDict: List<String>): List<String> {
    return wordBreakSentences(
        s,
        wordDict.toSet()
    ).map { it.joinToString(" ") }
}

fun wordBreakSentences(
    str: String,
    wordDict: Set<String>,
    startIndex: Int = 0,
    cache: MutableMap<Int, List<List<String>>> = mutableMapOf()
): List<List<String>> {
    cache[startIndex]?.let { return it }

    var word = ""
    val sentenceList = (startIndex..str.lastIndex).fold(emptyList<List<String>>()) { sentenceList, index ->
        word += str[index] // This is needed when you need to verify sequential accumulation, like a substring, where chars are next to each other.
        sentenceList + if (wordDict.contains(word)) {
            if (index == str.lastIndex) {
                listOf(listOf(word)) // If this is placed at the start of recursion, we need to update cache at two places.
            } else {
                wordBreakSentences(str, wordDict, index + 1, cache).map { listOf(word) + it }
            }
        } else {
            emptyList()
        }
    }
    // if the word can't be broken, the last recursion would return empty list, which shall be passed-on, so end results in empty list
    return cache.merge(startIndex, sentenceList) { old, new -> old + new } ?: emptyList()
}

fun main() {
    println(wordBreak(readLine()!!, readLine()!!.split(",").map { it.trim() }))
}