/* gakshintala created on 10/2/19 */
package leetcode.incomplete

fun findAllConcatenatedWordsInADict(words: Array<String>): List<String> {
    return words.filter { wordBreak(it, words) }
}

private fun wordBreak(s: String, wordDict: Array<String>): Boolean {
    val table = Array(s.length) { IntArray(s.length) }
    val wordDictSet = wordDict.toSet()
    when {
        s.isEmpty() -> return false
    }
    (0..s.lastIndex).filter { wordDict.contains(s[it].toString()) }.forEach { table[it][it] = 1 }

    /*for (gap in 1..s.length) {
        for ((i, j) in (gap..s.lastIndex).withIndex()) {
            table[i][j] = if (wordDictSet.contains(s.substring(i..j))) true else
                (i until j).fold(0) { res, partition ->
                    res + table[i][partition] + table[partition + 1][j]
                }
        }
    }*/
    return false
}

fun main() {
    findAllConcatenatedWordsInADict(
        arrayOf(
            "cat",
            "cats",
            "catsdogcats",
            "dog",
            "dogcatsdog",
            "hippopotamuses",
            "rat",
            "ratcatdogcat"
        )
    ).forEach { println(it) }
}