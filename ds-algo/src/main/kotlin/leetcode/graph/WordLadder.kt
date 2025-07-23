package leetcode.graph

fun ladderLength(beginWord: String, targetWord: String, wordList: List<String>): Int {
  val wordSet = wordList.toMutableSet()
  if (targetWord !in wordSet) return 0

  var start = ArrayDeque<String>().apply { add(beginWord) }
  var end = ArrayDeque<String>().apply { add(targetWord) }
  wordSet.removeAll(setOf(beginWord, targetWord))

  var transformations = 0
  // Transformations no more found in wordSet
  while (start.isNotEmpty()) {
    transformations++ // Each BFS level is counted as a transformation
    repeat(start.size) { // Deal with each level. `start.size` is evaluated only once
      val word = start.removeFirst()
      for (i in word.indices) {
        for (ch in 'a'..'z') {
          when (val transform = word.replaceRange(i, i + 1, ch.toString())) {
            // ! `+1` because the result needs no.of words = one more than the transformations
            in end -> return transformations + 1
            in wordSet -> {
              wordSet.remove(transform) // Marking it visited
              start.add(transform)
            }
          }
        }
      }
    }
    if (start.size > end.size) { // Bidirectional BFS, swapping start with the end.
      start = end.also { end = start }
    }
  }
  return 0
}
