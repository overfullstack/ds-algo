package ds

class TrieNode(val value: Char = Char.MIN_VALUE) { // First node of a Trie is a dummy.
  var isEnd = false
    private set
  var children = arrayOfNulls<TrieNode>(26)
    private set
  lateinit var word: String
    private set
  // set(value) = if (isEnd) field = value else throw AssertionError("Can't set word on non-end node")

  fun insert(key: String) {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: TrieNode(char).also {
        crawl.children[char - 'a'] = it
      }
    }
    crawl.isEnd = true
    crawl.word = key
  }

  fun remove(key: String, depth: Int = 0): TrieNode? {
    if (depth == key.length) {
      if (isEnd) {
        isEnd = false
      }
      return if (isEmptyChildren()) null else this // This has no sub-branches, the only branch is being totally dedicated for this word.
    }
    children[key[depth] - 'a'] = children[key[depth] - 'a']?.remove(key, depth + 1)
    return if (!isEnd && isEmptyChildren()) null else this // If current child is the only non-null child which got removed, remove this node recursively.
  }

  private fun isEmptyChildren(): Boolean {
    return children.all { it == null }
  }

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

  fun searchMultipleWords(key: String): Boolean {
    var crawl = this as TrieNode?
    for (char in key) {
      crawl =
        crawl?.let { if (it.isEnd) this.children[char - 'a'] else it.children[char - 'a'] }
        ?: return false
    }
    return crawl?.isEnd ?: false
  }

  fun recommendations(key: String): List<String> {
    var crawl = this
    for (char in key) {
      crawl = crawl.children[char - 'a'] ?: return emptyList()
    }
    return crawl.children.flatMap { it?.getRecommendations(key + it.value) ?: emptyList() }
  }

  private fun getRecommendations(prefix: String, curRecommendation: String = ""): List<String> =
    (
      (if (isEnd) listOf(prefix + curRecommendation) else emptyList()) +
        children.asSequence().filterNotNull()
          .flatMap { // It ends at leaf when all children are null
            val recommendation = curRecommendation + it.value
            it.getRecommendations(prefix, recommendation) // dfs
          }
      )
}
