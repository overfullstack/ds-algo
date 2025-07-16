package ds

open class TrieNode(val value: Char = Char.MIN_VALUE) { // The First node of a Trie is a stub.
  var isEnd = false
    private set

  var children = arrayOfNulls<TrieNode>(26)
    private set

  lateinit var word: String
    private set
  
  fun insert(key: String) {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: TrieNode(char).also { crawl.children[char - 'a'] = it }
    }
    crawl.isEnd = true
    crawl.word = key
  }

  fun remove(key: String, depth: Int = 0): TrieNode? {
    if (depth == key.length) {
      if (isEnd) {
        isEnd = false
      }
      // This has no subbranches, the only branch is being totally dedicated for this word.
      return if (isEmptyChildren()) null else this
    }
    children[key[depth] - 'a'] = children[key[depth] - 'a']?.remove(key, depth + 1)
    return if (!isEnd && isEmptyChildren()) null
    else this // If the current child is the only non-null child that got removed, remove this node
    // recursively.
  }

  private fun isEmptyChildren(): Boolean = children.all { it == null }

  fun startsWith(prefix: String): Boolean {
    var crawl = this
    for (char in prefix) {
      crawl = crawl.children[char - 'a'] ?: return false
    }
    return true
  }

  fun isPresent(key: String): Boolean {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: return false
    }
    return crawl.isEnd
  }

  fun isCombinationPresent(key: String): Boolean {
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
      // + 1 coz the first level is stub
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

  private fun dfsWords(prefix: String, limit: Int, curWord: String = ""): List<String> {
    val currentWord = if (isEnd) listOf(prefix + curWord) else emptyList()
    val remainingLimit = limit - currentWord.size
    val wordsFromChildren =
      children.asSequence().filterNotNull().fold(emptyList<String>()) { words, child ->
        when {
          words.size >= remainingLimit -> words
          else -> words + child.dfsWords(prefix, remainingLimit - words.size, curWord + child.value)
        }
      }
    return currentWord + wordsFromChildren
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
