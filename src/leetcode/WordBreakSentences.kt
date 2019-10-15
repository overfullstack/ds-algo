/* gakshintala created on 10/2/19 */
package leetcode

private fun wordBreak(s: String, wordDict: List<String>): List<String> {
    return wordBreakSentences(
        s,
        wordDict.toSet(),
        0,
        mutableMapOf()
    ).map { it.joinToString(" ") }
}

fun wordBreakSentences(
    s: String,
    wordDict: Set<String>,
    startIndex: Int,
    cache: MutableMap<Int, List<List<String>>>
): List<List<String>> {
    if (cache.containsKey(startIndex)) {
        return cache[startIndex]!!
    }
    var subStr = ""
    val sentenceList = emptyList<List<String>>().toMutableList()
    for (i in startIndex..s.lastIndex) {
        subStr += s[i]
        if (wordDict.contains(subStr)) {
            if (i == s.lastIndex) {
                sentenceList += mutableListOf(listOf(subStr))
            } else {
                val wordBreakSentences = wordBreakSentences(s, wordDict, i + 1, cache)
                sentenceList += wordBreakSentences.map { listOf(subStr) + it } // Prefixing with current substr.
            }
        }
    }
    cache.merge(startIndex, sentenceList) { old, new -> old + new }
    return sentenceList
}

fun main() {
    /*println(wordBreak("leetcode", listOf("leet", "code")))
    println(wordBreak("catsanddog", listOf("cat", "cats", "and", "sand", "dog")))
    println(wordBreak("pineapplepenapple", listOf("apple", "pen", "applepen", "pine", "pineapple")))*/
    println(wordBreak("abcd", listOf("a", "abc", "b", "cd")))
    /*println(
        wordBreak(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")
        )
    )*/

}