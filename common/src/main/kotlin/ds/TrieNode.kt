package ds

class TrieNode(val value: Char = Char.MIN_VALUE) { // The First node of a Trie is a dummy.
  var isEnd = false
    private set

  var children = arrayOfNulls<TrieNode>(26)
    private set

  lateinit var word: String
    private set

  // set(value) = if (isEnd) field = value else throw AssertionError("Can't set word on non-end
  // node")

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

  fun search(key: String): Boolean {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: return false
    }
    return crawl.isEnd
  }

  fun searchWordCombination(key: String): Boolean {
    var crawl = this as TrieNode?
    for (char in key) {
      // If we find the end, start from the beginning
      crawl =
        crawl?.let { if (it.isEnd) this.children[char - 'a'] else it.children[char - 'a'] }
          ?: return false
    }
    return crawl?.isEnd ?: false
  }

  fun recommendationsWhileTyping(key: String, limit: Int = Int.MAX_VALUE): List<List<String>> =
    key.map { it.toString() }.runningReduce(String::plus).map { recommendations(it, limit) }

  fun recommendations(key: String, limit: Int = Int.MAX_VALUE): List<String> {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: return emptyList()
    }
    return crawl.getRecommendations(key, limit)
  }

  private fun getRecommendations(
    prefix: String,
    limit: Int,
    curRecommendation: String = "",
  ): List<String> {
    val currentWord = if (isEnd) listOf(prefix + curRecommendation) else emptyList()
      val remainingLimit = limit - currentWord.size
      val childRecommendations =
        children.asSequence().filterNotNull().fold(emptyList<String>()) { acc, child ->
          when {
            acc.size >= remainingLimit -> acc
            else ->
              acc +
                child.getRecommendations(
                  prefix,
                  remainingLimit - acc.size,
                  curRecommendation + child.value,
                )
          }
        }
      return currentWord + childRecommendations
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
