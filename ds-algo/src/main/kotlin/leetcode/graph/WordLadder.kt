package leetcode.graph

/** [127. Word Ladder](https://leetcode.com/problems/word-ladder/) */
fun ladderLength(beginWord: String, targetWord: String, wordList: List<String>): Int {
  val wordSet = wordList.toMutableSet()
  if (targetWord !in wordSet) return 0
  var start = setOf(beginWord)
  var end = setOf(targetWord)
  wordSet.removeAll(setOf(beginWord, targetWord)) // ! visited

  var transformations = 0
  while (start.isNotEmpty()) {
    transformations++ // ! Each BFS level is counted as a transformation
    val nextLevel = mutableSetOf<String>()
    for (word in start) {
      for (i in word.indices) {
        for (ch in 'a'..'z') {
          when (val transform = word.replaceRange(i, i + 1, ch.toString())) {
            // ! `+1` because the result needs no.of words = one more than the transformations
            in end -> return transformations + 1
            in wordSet -> {
              wordSet.remove(transform) // ! Marking it visited
              nextLevel.add(transform)
            }
          }
        }
      }
    }
    start = nextLevel
    if (start.size > end.size) { // * Bidirectional BFS, swapping start with the end.
      start = end.also { end = start }
    }
  }
  return 0
}

fun main() {
  println(ladderLength("hit", "cog", listOf("hot", "dot", "dog", "lot", "log", "cog"))) // 5
  println(ladderLength("hit", "cog", listOf("hot", "dot", "dog", "lot", "log"))) // 0
}
