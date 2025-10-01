package educative.graph

/* 30 Aug 2024 10:46 */

/** [892 · Alien Dictionary](https://www.lintcode.com/problem/892/) */
fun orderAlienDictionary(words: Array<String>): String { // * Kahn's algorithm
  val diGraph: MutableMap<Char, Set<Char>> = mutableMapOf()
  val charToInConnectionCount = words.flatMap { it.toList() }.associateWith { 0 }.toMutableMap()
  val uniqueCharCount = charToInConnectionCount.size
  for (i in 0 until words.lastIndex) {
    val unmatchedResult = firstUnmatchedLetterPair(words[i], words[i + 1])
    unmatchedResult?.fold(
      { (fromChar, toChar) ->
        if (diGraph[fromChar]?.contains(toChar) != true) {
          diGraph.merge(fromChar, setOf(toChar), Set<Char>::plus)
          charToInConnectionCount.computeIfPresent(toChar) { _, value -> value.inc() }
        }
      },
      {
        return "" // ! If there is an exception
      },
    )
  }
  val result = mutableListOf<Char>()
  // Adding leaf nodes to queue
  val queue = ArrayDeque(charToInConnectionCount.filterValues { it == 0 }.map { it.key })
  while (queue.isNotEmpty()) {
    val nextChar = queue.removeFirst()
    result += nextChar
    diGraph[nextChar]?.forEach {
      charToInConnectionCount.computeIfPresent(it) { _, inConnectionCount ->
        when (inConnectionCount) {
          1 -> {
            queue.add(it)
            null
          }

          else -> inConnectionCount.dec()
        }
      }
    }
  }

  return when {
    result.size < uniqueCharCount -> ""
    else -> result.joinToString("")
  }
}

private fun firstUnmatchedLetterPair(word1: String, word2: String): Result<Pair<Char, Char>>? =
  runCatching {
    var i = 0
    while (i <= word1.lastIndex) {
      // ! if word1 is prefix of word2, word1 should come before word2
      require(i <= word2.lastIndex)
      if (word1[i] != word2[i]) {
        return Result.success(word1[i] to word2[i])
      }
      i++
    }
    return null
  }

fun main() {
  println(orderAlienDictionary(arrayOf("zy", "zx"))) // "zyx"
}
