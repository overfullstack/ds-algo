package educative.graph

import ds.graph.DiGraph

/* 30 Aug 2024 10:46 */

fun orderAlienDictionary(words: List<String>): String {
  val diGraph = DiGraph<Char>()
  val charToInConnection = words.flatMap { it.toList() }.associateWith { 0 }.toMutableMap()
  for (i in (0..words.lastIndex - 1)) {
    val unmatchedResult = firstUnmatchedLetterPair(words[i], words[i + 1])
    unmatchedResult?.fold(
      { (char1, char2) ->
        if (diGraph[char1]?.contains(char2) != true) {
          diGraph.addEdge(char1, char2)
          charToInConnection.computeIfPresent(char2) { _, value -> value.inc() }
        }
      },
      {
        return ""
      }
    )
  }
  val result = mutableListOf<Char>()
  val queue =
    ArrayDeque<Char>(
      charToInConnection.mapNotNull { (key, value) -> if (value == 0) key else null }
    )
  while (queue.isNotEmpty()) {
    val nextChar = queue.removeFirst()
    result.add(nextChar)
    diGraph[nextChar]?.forEach {
      charToInConnection.computeIfPresent(it) { _, value ->
        val newValue = value.dec()
        if (newValue == 0) {
          queue.add(it)
        }
        newValue
      }
    }
  }

  return when {
    result.size < charToInConnection.size -> ""
    else -> result.joinToString("")
  }
}

fun firstUnmatchedLetterPair(word1: String, word2: String): Result<Pair<Char, Char>>? =
  runCatching {
    var i = 0
    while (i <= word1.lastIndex) {
      require(i <= word2.lastIndex)
      if (word1[i] != word2[i]) {
        return Result.success(word1[i] to word2[i])
      }
      i++
    }
    return null
  }
