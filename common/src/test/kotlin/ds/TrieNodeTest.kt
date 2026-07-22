package ds

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import testcase.TestCases

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
      TestCases.load("trie/Recommendations/test-cases-1.json").forAll { case ->
        val products = case.input<List<String>>(1)
        val searchKey = case.input<String>(2)
        val output = case.output<List<List<String>>>(1)
        trieNode = TrieNode()
        products.forEach { trieNode.insert(it) }
        trieNode.recommendationsWhileTyping(searchKey, 3) shouldBe output
      }
    }

    "recommendations should return all words that start with the given prefix 2" {
      trieNode.insert("gopal")
      trieNode.insert("gopals")
      trieNode.insert("go")

      // "go" is itself a stored word with the prefix, so it's included
      trieNode.recommendations("go") shouldContainExactlyInAnyOrder listOf("go", "gopal", "gopals")
    }

    "operate" {
      TestCases.load("trie/Operations/test-cases-1.json").forAll { case ->
        val operations = case.input<List<String>>(1)
        val args = case.input<List<List<String>>>(2)
        val output = case.output<List<String>>(1)
        trieNode = TrieNode()
        operations
          .zip(args)
          .map { (operation, arg) -> trieNode.operate(operation, arg.firstOrNull() ?: "") }
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
