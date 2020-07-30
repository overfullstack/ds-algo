package ds

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TrieNodeTest {

    lateinit var trieNode: TrieNode

    @BeforeEach
    internal fun setUp() {
        trieNode = TrieNode()
    }

    @Test
    fun insert() {
        trieNode.insert("gopal")
        trieNode.search("gopal") shouldBe true
    }

    @Test
    fun `insert overlapping words`() {
        trieNode.insert("gopal")
        trieNode.insert("go")

        trieNode.search("gopal") shouldBe true
        trieNode.search("go") shouldBe true
    }

    @Test
    fun `search multiple not present`() {
        trieNode.insert("gopal")
        trieNode.insert("sarma")
        trieNode.searchMultipleWords("gopalasarma") shouldBe false
    }

    @Test
    fun `search multiple present`() {
        trieNode.insert("gopal")
        trieNode.insert("sarma")
        trieNode.searchMultipleWords("gopalsarma") shouldBe true
    }

    @Test
    fun `remove sub word that doesn't exist`() {
        trieNode.insert("gopal")
        trieNode.remove("go")
        trieNode.search("gopal") shouldBe true
    }

    @Test
    fun `remove word that doesn't exist`() {
        trieNode.insert("gopal")
        shouldNotThrowAny { trieNode.remove("sarma") }
        trieNode.search("gopal") shouldBe true
    }

    @Test
    fun `remove sub word should preserve super word`() {
        trieNode.insert("gopal")
        trieNode.insert("gopals")
        trieNode.insert("go")

        trieNode.remove("go")

        trieNode.search("go") shouldBe false
        trieNode.search("gopal") shouldBe true
    }

    @Test
    fun `removing super word should preserve all sub words`() {
        trieNode.insert("gopal")
        trieNode.insert("gopals")
        trieNode.insert("go")

        trieNode.remove("gopals")

        trieNode.search("gopals") shouldBe false
        trieNode.search("go") shouldBe true
        trieNode.search("gopal") shouldBe true
    }

    @Test
    fun `get recommendations`() {
        trieNode.insert("gopal")
        trieNode.insert("gopals")
        trieNode.insert("go")

        trieNode.recommendations("go") shouldContainExactlyInAnyOrder listOf("gopal", "gopals")
    }

}
