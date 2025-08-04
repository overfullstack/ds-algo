package ds

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import testcase.TestCase23
import testcase.TestCase24

internal class TrieNodeTest :
  StringSpec({
    lateinit var trieNode: TrieNode

    beforeEach { trieNode = TrieNode() }

    "insert should add a word to the trie" {
      trieNode.insert("gopal")
      trieNode.isPresent("gopal") shouldBe true
    }

    "insert should handle overlapping words correctly" {
      trieNode.insert("gopal")
      trieNode.insert("go")

      trieNode.isPresent("gopal") shouldBe true
      trieNode.isPresent("go") shouldBe true
    }

    "searchWordCombination should return false for non-present word combinations" {
      trieNode.insert("gopal")
      trieNode.insert("sarma")
      trieNode.isWordSequencePresent("gopalasarma") shouldBe false
    }

    "searchWordCombination should return true for present word combinations" {
      trieNode.insert("gopal")
      trieNode.insert("sarma")
      trieNode.isWordSequencePresent("gopalsarma") shouldBe true
    }

    "remove should not affect super words when removing non-existent sub words" {
      trieNode.insert("gopal")
      trieNode.remove("go")
      trieNode.isPresent("gopal") shouldBe true
    }

    "remove should not throw exception when removing non-existent words" {
      trieNode.insert("gopal")
      shouldNotThrowAny { trieNode.remove("sarma") }
      trieNode.isPresent("gopal") shouldBe true
    }

    "remove should preserve super words when removing sub words" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      trieNode.remove("go")

      trieNode.isPresent("go") shouldBe false
      trieNode.isPresent("gopal") shouldBe true
    }

    "remove should preserve all sub words when removing super words" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      trieNode.remove("gopals")

      trieNode.isPresent("gopals") shouldBe false
      trieNode.isPresent("go") shouldBe true
      trieNode.isPresent("gopal") shouldBe true
    }

    "recommendations by typing one letter at a time" {
      TestCase23.parseJsonFileToTestCases("trie/Recommendations/test-cases-1.json").forAll {
        (input, output) ->
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

    "operate" {
      TestCase24.parseJsonFileToTestCases("trie/Operations/test-cases-1.json").forAll {
        (inputs, output) ->
        trieNode = TrieNode()
        inputs
          .map { (operation, arg) -> trieNode.operate(operation, arg) }
          .map {
            when (it) {
              is Unit -> "null"
              else -> it.toString()
            }
          } shouldBe output
      }
    }
  })

fun TrieNode.operate(operation: String, arg: String): Any? =
  when (operation) {
    "addWord" -> insert(arg)
    "getWords" -> getAllWords()
    "searchWord" -> isDotRegexPresent(arg)
    else -> null
  }
