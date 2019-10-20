/* gakshintala created on 9/19/19 */
package misc

class Trie(val value: Char = Char.MIN_VALUE) {
    private var isEnd = false
    private val children = arrayOfNulls<Trie>(26)

    fun insert(key: String) {
        var crawl = this
        for (char in key) {
            crawl.children[char - 'a'] = crawl.children[char - 'a'] ?: Trie(char)
            crawl = crawl.children[char - 'a']!!
        }
        crawl.isEnd = true
    }

    fun search(key: String): Boolean {
        var crawl = this
        for (char in key) {
            crawl = crawl.children[char - 'a'] ?: return false
        }
        return crawl.isEnd
    }

    fun searchMulti(key: String): Boolean {
        val root = this
        var crawl: Trie? = this
        for (char in key) {
            if (crawl == null) {
                return false
            }
            crawl = crawl.children[char - 'a'] ?: root.children[char - 'a']
        }
        return crawl?.isEnd ?: false
    }

    fun recommendations(key: String) {
        var crawl = this
        for (char in key) {
            crawl = crawl.children[char - 'a'] ?: return
        }
        printRecommendations(crawl, key)
    }

    private fun printRecommendations(trieNode: Trie, curPrefix: String) {
        if (trieNode.isEnd) {
            println(curPrefix)
        } else {
            trieNode.children.filterNotNull().forEach { printRecommendations(it, curPrefix + it.value) }
        }
    }
}

fun main() {
    val trie = Trie()
    trie.insert("gopal")
    trie.insert("gopala")
    trie.insert("sarma")
    trie.insert("akshintala")
    trie.insert("sai")
    println(trie.search("gopal"))
    println(trie.search("gopa"))
    println(trie.search("akshintala"))
    println(trie.search("sa"))

    trie.recommendations("go")

    println(trie.searchMulti("gopalsarma"))
    println(trie.searchMulti("gopalsakshintala"))
}