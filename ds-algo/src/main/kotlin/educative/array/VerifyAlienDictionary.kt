package educative.array

/* 28 Aug 2024 09:09 */
fun verifyAlienDictionary(words: Array<String>, order: String): Boolean {
  val letterRanking = order.mapIndexed { index, char -> char to index }.toMap()
  for (index in (0..words.lastIndex - 1)) {
    val word1 = words[index]
    val word2 = words[index + 1]
    var i = 0
    while (i <= word1.lastIndex) {
      if (i >= word2.length) {
        return false
      }
      if (word1[i] != word2[i]) {
        when {
          letterRanking[word1[i]]!! > letterRanking[word2[i]]!! -> return false
          else -> break
        }
      }
      i++
    }
  }
  return true
}
