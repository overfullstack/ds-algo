package leetcode.graph

import java.util.*


fun ladderLength(beginWord: String, endWord: String, wordAsList: List<String>): Int {
    if (!wordAsList.contains(endWord)) return 0

    val wordList: MutableSet<String> = HashSet<String>(wordAsList)
    var start: MutableSet<String> = HashSet()
    var end: MutableSet<String> = HashSet()
    var length = 1
    start.add(beginWord)
    end.add(endWord)
    wordList.remove(beginWord)
    wordList.remove(endWord)

    while (start.isNotEmpty()) {
        val next: MutableSet<String> = HashSet()
        for (word in start) {
            val wordArray = word.toCharArray()
            for (i in word.indices) {
                val old = wordArray[i]
                var c = 'a'
                while (c <= 'z') {
                    wordArray[i] = c
                    val str = String(wordArray)
                    if (end.contains(str)) {
                        return length + 1
                    }
                    if (wordList.contains(str)) {
                        next.add(str)
                        wordList.remove(str)
                    }
                    c++
                }
                wordArray[i] = old
            }
        }
        start = if (next.size < end.size) next else end
        end = if (start.size < end.size) end else next
        length++
    }
    return 0
}

fun main() {
    println(ladderLength("hit", "dot", listOf("hot", "dot", "dog", "lot", "log", "cog")))
}
