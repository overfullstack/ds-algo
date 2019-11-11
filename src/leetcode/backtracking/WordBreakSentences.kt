/* gakshintala created on 10/2/19 */
package leetcode.backtracking

private fun wordBreakSentences(s: String, wordDict: List<String>): List<String> {
    return wordBreakSentences(
        s,
        wordDict.toSet(),
        0,
        mutableMapOf()
    ).map { it.joinToString(" ") }
}

fun wordBreakSentences(
    str: String,
    wordDict: Set<String>,
    startIndex: Int,
    cache: MutableMap<Int, List<List<String>>>
): List<List<String>> {
    if (cache.containsKey(startIndex)) {
        return cache[startIndex]!!
    }
    var subStr = ""
    val sentenceList = emptyList<List<String>>().toMutableList()
    for (i in startIndex..str.lastIndex) {
        subStr += str[i]
        if (wordDict.contains(subStr)) {
            sentenceList +=
                if (i == str.lastIndex) {
                    mutableListOf(listOf(subStr))
                } else {
                    wordBreakSentences(
                        str,
                        wordDict,
                        i + 1,
                        cache
                    ).map { listOf(subStr) + it } // Prefixing with current substr.
                }
        }
    }
    cache.merge(startIndex, sentenceList) { old, new -> old + new }
    return sentenceList
}

fun main() {
    println(wordBreakSentences("leetcode", listOf("leet", "code")))
    println(
        wordBreakSentences(
            "catsanddog",
            listOf("cat", "cats", "and", "sand", "dog")
        )
    )
    println(
        wordBreakSentences(
            "pineapplepenapple",
            listOf("apple", "pen", "applepen", "pine", "pineapple")
        )
    )
    println(wordBreakSentences("abcd", listOf("a", "abc", "b", "cd")))
    println(
        wordBreakSentences(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")
        )
    )

}