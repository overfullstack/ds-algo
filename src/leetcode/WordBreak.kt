/* gakshintala created on 10/2/19 */
package leetcode

private fun wordBreak(s: String, wordDict: List<String>): Boolean {
    val table = Array(s.length) { BooleanArray(s.length) }
    val wordDictSet = wordDict.toSet()
    when {
        s.isEmpty() -> return false
        wordDict.contains(s) -> return true
    }
    (0..s.lastIndex).forEach { table[it][it] = wordDict.contains(s[it].toString()) }

    for (gap in 1..s.length) {
        for ((i, j) in (gap..s.lastIndex).withIndex()) {
            table[i][j] = if (wordDictSet.contains(s.substring(i..j))) true else
                (i until j).fold(false) { res, partition ->
                    res || (table[i][partition] && table[partition + 1][j])
                }
        }
    }
    return table[0][s.lastIndex]
}

private fun wordBreakOptimized(s: String, wordDict: List<String>): Boolean {
    val wordDictSet = wordDict.toSet()
    return s.indices.fold(mutableListOf(0)) { wordEndIndices, strIndex ->
        if (wordEndIndices.any { wordDictSet.contains(s.substring(it..strIndex)) }) {
            wordEndIndices.add(strIndex + 1)
        }
        wordEndIndices
    }.last() - 1 == s.lastIndex
}

fun main() {
    println(wordBreakOptimized("leetcode", listOf("leet", "code")))
    println(wordBreakOptimized("applepenapple", listOf("apple", "pen")))
    println(wordBreakOptimized("catsandog", listOf("cats", "dog", "sand", "and", "cat")))
    println(wordBreakOptimized("a", listOf("a")))
    println(wordBreakOptimized("ab", listOf("a", "b")))
}