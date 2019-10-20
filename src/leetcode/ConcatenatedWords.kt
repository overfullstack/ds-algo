/* gakshintala created on 10/2/19 */
package leetcode

import java.util.*

fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
    val nonEmptyWords = words.filter { it.isNotEmpty() }
    val wordLengthStats = nonEmptyWords.fold(IntSummaryStatistics()) { stats, word ->
        stats.also { it.accept(word.length) }
    }
    val eligibleWords = nonEmptyWords.filter { it.length in wordLengthStats.min * 2..wordLengthStats.max }
    return eligibleWords.filter {
        val wordSetWithoutCurWord = nonEmptyWords.toMutableSet()
        wordSetWithoutCurWord.remove(it)
        isWordPresent(it, wordSetWithoutCurWord, wordLengthStats.min)
    }
}

private fun isWordPresent(word: String, wordDictSet: Set<String>, minLength: Int) =
    word.indices.drop(minLength - 1).fold(mutableListOf(-1)) { wordEndIndices, wordIndex ->
        wordEndIndices.also {
            if (it.any { endIndex -> wordDictSet.contains(word.substring(endIndex + 1..wordIndex)) }) {
                it.add(wordIndex)
            }
        }
    }.last() == word.lastIndex

/*private fun isWordPresent(word: String, wordDictSet: Set<String>, minLength: Int): Boolean {
    for (i in minLength..word.lastIndex) {
        if (wordDictSet.contains(word.substring(0, i))
            && (wordDictSet.contains(word.substring(i)) || isWordPresent(word.substring(i), wordDictSet, minLength))
        ) {
            return true
        }
    }
    return false
}*/


fun main() {
    findAllConcatenatedWordsInADict(
        arrayOf(
            "ab",
            "cat",
            "cats",
            "catsdogcats",
            "dog",
            "dogcatsdog",
            "hippopotamuses",
            "rat",
            "ratcatdogcat",
            ""
        )
    ).forEach { println(it) }
}