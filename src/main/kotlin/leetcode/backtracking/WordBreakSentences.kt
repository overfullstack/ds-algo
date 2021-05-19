/* gakshintala created on 10/2/19 */
package leetcode.backtracking

fun wordBreakSentences(s: String, wordDict: List<String>): List<String> {
    return wordBreakSentences(
        s,
        wordDict.toSet()
    ).map { it.joinToString(" ") }
}

private fun wordBreakSentences(
    str: String,
    wordDict: Set<String>,
    startIndex: Int = 0,
    cache: MutableMap<Int, List<List<String>>> = mutableMapOf()
): List<List<String>> {
    cache[startIndex]?.let { return it }
    val sentenceList =
        (startIndex..str.lastIndex)
            // filter for all the points which can be broken as word for this recursion.
            .filter { wordDict.contains(str.substring(startIndex..it)) }
            .flatMap {
                val word = str.substring(startIndex..it)
                if (it == str.lastIndex) {
                    listOf(listOf(word)) // If this is placed at the start of recursion, we need to update cache at two places.
                } else { // Sentence append can't be made eager, as this result is directly put into cache.
                    wordBreakSentences(
                        str,
                        wordDict,
                        it + 1,
                        cache
                    ).map { sentence -> listOf(word) + sentence }
                }
            }
    // If the word can't be broken, the last recursion would return empty list, which shall be passed-on, so end results in empty list
    return cache.merge(startIndex, sentenceList, Collection<List<String>>::plus)!!
}

fun main() {
    println(wordBreakSentences(readLine()!!, readLine()!!.split(",").map { it.trim() }))
}
