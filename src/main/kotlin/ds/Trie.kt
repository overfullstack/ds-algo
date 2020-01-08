package ds

class Trie(val value: Char = Char.MIN_VALUE) {
    var isEnd = false
        private set
    var children = arrayOfNulls<Trie>(26)
        private set
    lateinit var word: String
        private set
    //set(value) = if (isEnd) field = value else throw AssertionError("Can't set word on non-end node")

    fun insert(key: String) {
        var crawl = this
        for (char in key) {
            crawl = crawl.children[char - 'a'] ?: Trie(char).also { crawl.children[char - 'a'] = it }
        }
        crawl.isEnd = true
        crawl.word = key
    }

    fun remove(key: String) {
        removeKey(key)
    }

    private fun removeKey(key: String, depth: Int = 0): Trie? {
        if (depth == key.length) {
            if (isEnd) {
                isEnd = false
            }
            return if (isEmptyChildren()) null else this
        }
        children[key[depth] - 'a'] = children[key[depth] - 'a']?.removeKey(key, depth + 1)
        return if (!isEnd && isEmptyChildren()) null else this
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
        var crawl = this as Trie?
        for (char in key) {
            crawl = crawl?.let { if (it.isEnd) this.children[char - 'a'] else it.children[char - 'a'] } ?: return false
        }
        return crawl?.isEnd ?: false
    }

    fun recommendations(key: String) {
        var crawl = this
        for (char in key) {
            crawl = crawl.children[char - 'a'] ?: return
        }
        crawl.printRecommendations(key)
    }

    private fun printRecommendations(curPrefix: String) {
        if (isEnd) {
            println(curPrefix)
        }
        children.filterNotNull().forEach { printRecommendations(curPrefix + it.value) } // dfs
    }
}