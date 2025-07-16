package ds

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import testcase.TestCase23.Companion.parseJsonFileToTestCases

private const val PKG_PATH = "trie"

internal class TrieNodeTest :
  StringSpec({
    lateinit var trieNode: TrieNode

    beforeEach { trieNode = TrieNode() }

    "insert should add a word to the trie" {
      trieNode.insert("gopal")
      trieNode.search("gopal") shouldBe true
    }

    "insert should handle overlapping words correctly" {
      trieNode.insert("gopal")
      trieNode.insert("go")

      trieNode.search("gopal") shouldBe true
      trieNode.search("go") shouldBe true
    }

    "searchWordCombination should return false for non-present word combinations" {
      trieNode.insert("gopal")
      trieNode.insert("sarma")
      trieNode.searchWordCombination("gopalasarma") shouldBe false
    }

    "searchWordCombination should return true for present word combinations" {
      trieNode.insert("gopal")
      trieNode.insert("sarma")
      trieNode.searchWordCombination("gopalsarma") shouldBe true
    }

    "remove should not affect super words when removing non-existent sub words" {
      trieNode.insert("gopal")
      trieNode.remove("go")
      trieNode.search("gopal") shouldBe true
    }

    "remove should not throw exception when removing non-existent words" {
      trieNode.insert("gopal")
      shouldNotThrowAny { trieNode.remove("sarma") }
      trieNode.search("gopal") shouldBe true
    }

    "remove should preserve super words when removing sub words" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      trieNode.remove("go")

      trieNode.search("go") shouldBe false
      trieNode.search("gopal") shouldBe true
    }

    "remove should preserve all sub words when removing super words" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      trieNode.remove("gopals")

      trieNode.search("gopals") shouldBe false
      trieNode.search("go") shouldBe true
      trieNode.search("gopal") shouldBe true
    }

    "recommendations by typing one letter at a time" {
      parseJsonFileToTestCases("${PKG_PATH}/test-cases-1.json").forAll { (input, output) ->
        val (products, searchKey) = input
        trieNode = TrieNode()
        products.forEach { trieNode.insert(it) }
        trieNode.recommendationsWhileTyping(searchKey, 3) shouldBe output
      }
    }

    "recommendations should return all words that start with the given prefix 2" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      trieNode.recommendations("go") shouldContainExactlyInAnyOrder listOf("gopal", "gopals")
    }
  })
