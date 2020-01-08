package ds

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrowAny
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TrieTest {

    lateinit var trie: Trie

    @BeforeEach
    internal fun setUp() {
        trie = Trie()
    }

    @Test
    fun insert() {
        trie.insert("gopal")
        trie.search("gopal") shouldBe true
    }

    @Test
    fun `insert overlapping words`() {
        trie.insert("gopal")
        trie.insert("go")

        trie.search("gopal") shouldBe true
        trie.search("go") shouldBe true
    }

    @Test
    fun `search multiple not present`() {
        trie.insert("gopal")
        trie.insert("sarma")
        trie.searchMultipleWords("gopalasarma") shouldBe false
    }

    @Test
    fun `search multiple present`() {
        trie.insert("gopal")
        trie.insert("sarma")
        trie.searchMultipleWords("gopalsarma") shouldBe true
    }

    @Test
    fun `remove sub word that doesn't exist`() {
        trie.insert("gopal")
        trie.remove("go")
        trie.search("gopal") shouldBe true
    }

    @Test
    fun `remove word that doesn't exist`() {
        trie.insert("gopal")
        shouldNotThrowAny { trie.remove("sarma") }
        trie.search("gopal") shouldBe true
    }

    @Test
    fun `remove sub word should preserve super word`() {
        trie.insert("gopal")
        trie.insert("gopals")
        trie.insert("go")

        trie.remove("go")

        trie.search("go") shouldBe false
        trie.search("gopal") shouldBe true
    }

    @Test
    fun `removing super word should preserve all sub words`() {
        trie.insert("gopal")
        trie.insert("gopals")
        trie.insert("go")

        trie.remove("gopals")

        trie.search("gopals") shouldBe false
        trie.search("go") shouldBe true
        trie.search("gopal") shouldBe true
    }

}