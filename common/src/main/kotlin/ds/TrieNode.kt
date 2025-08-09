package ds

data class TrieNode(val value: Char = Char.MIN_VALUE) { // The First node of a Trie is a stub.
  var isEnd = false
    private set

  var children = arrayOfNulls<TrieNode>(26)
    private set

  lateinit var word: String
    private set

  fun insert(word: String) {
    var crawl = this
    for (char in word) {
      crawl = crawl.children[char - 'a'] ?: TrieNode(char).also { crawl.children[char - 'a'] = it }
    }
    crawl.isEnd = true
    crawl.word = word
  }

  fun isPresent(word: String): Boolean {
    var crawl = this
    for (char in word) {
      crawl = crawl.children[char - 'a'] ?: return false
    }
    return crawl.isEnd
  }

  fun remove(word: String, depth: Int = 0): TrieNode? {
    when (depth) {
      word.length -> if (isEnd) isEnd = false
      else -> children[word[depth] - 'a'] = children[word[depth] - 'a']?.remove(word, depth + 1)
    }
    // remove node if it has no words ending here and no children
    return if (!isEnd && isEmptyChildren()) null else this
  }

  private fun isEmptyChildren(): Boolean = children.all { it == null }

  fun startsWith(prefix: String): Boolean {
    var crawl = this
    for (char in prefix) {
      crawl = crawl.children[char - 'a'] ?: return false
    }
    return true
  }

  fun isWordSequencePresent(key: String): Boolean {
    var crawl = this as TrieNode?
    for (char in key) {
      // If we find the end, start from the beginning
      crawl =
        crawl?.let { if (it.isEnd) this.children[char - 'a'] else it.children[char - 'a'] }
          ?: return false
    }
    return crawl?.isEnd ?: false
  }

  // * We can also use `dfsWords("", Int.MAX_VALUE)`
  fun getAllWords(word: String = ""): List<String> =
    (if (isEnd) listOf(word) else emptyList()) +
      children.asSequence().filterNotNull().flatMap { it.getAllWords(word + it.value) }

  fun getAllWordsWithDotRegex(
    dotRegex: String,
    dotRegexIndex: Int = 0,
    word: String = "",
  ): List<String> =
    (if (isEnd) listOf(word) else emptyList()) +
      children
        .asSequence()
        .filterNotNull()
        .filter { dotRegex[dotRegexIndex] == '.' || dotRegex[dotRegexIndex] == it.value }
        .flatMap { it.getAllWordsWithDotRegex(dotRegex, dotRegexIndex + 1, word + it.value) }

  fun isDotRegexPresent(dotRegex: String, dotRegexIndex: Int = 0): Boolean =
    when {
      // ! + 1 coz the first level is a stub
      dotRegexIndex == dotRegex.lastIndex + 1 -> isEnd
      else ->
        children
          .asSequence()
          .filterNotNull()
          .filter { dotRegex[dotRegexIndex] == '.' || dotRegex[dotRegexIndex] == it.value }
          .any { it.isDotRegexPresent(dotRegex, dotRegexIndex + 1) }
    }

  fun recommendationsWhileTyping(key: String, limit: Int = Int.MAX_VALUE): List<List<String>> =
    key.map { it.toString() }.runningReduce(String::plus).map { recommendations(it, limit) }

  fun recommendations(key: String, limit: Int = Int.MAX_VALUE): List<String> {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: return emptyList()
    }
    return crawl.dfsWords(key, limit)
  }

  private fun dfsWords(prefix: String, limit: Int, suffix: String = ""): List<String> {
    val currentWords = if (isEnd) listOf(prefix + suffix) else emptyList()
    val remainingLimit = limit - currentWords.size // ! `currentWords.size` is 0 or 1
    val wordsFromChildren =
      children.asSequence().filterNotNull().fold(emptyList<String>()) { words, child ->
        when {
          words.size == remainingLimit -> return currentWords + words.take(remainingLimit)
          else -> words + child.dfsWords(prefix, remainingLimit - words.size, suffix + child.value)
        }
      }
    return currentWords + wordsFromChildren
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as TrieNode

    if (value != other.value) return false
    if (isEnd != other.isEnd) return false
    if (!children.contentEquals(other.children)) return false
    if (word != other.word) return false

    return true
  }

  override fun hashCode(): Int {
    var result = value.hashCode()
    result = 31 * result + isEnd.hashCode()
    result = 31 * result + children.contentHashCode()
    result = 31 * result + word.hashCode()
    return result
  }
}

fun main() {
  val trie = TrieNode()
  trie.insert("bags")
  trie.insert("baggage")
  trie.insert("banner")
  trie.insert("box")
  trie.insert("cloths")
  println(trie.recommendationsWhileTyping("bags", 3))
}
