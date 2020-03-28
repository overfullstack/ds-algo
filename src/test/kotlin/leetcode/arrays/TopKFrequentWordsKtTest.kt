package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TopKFrequentWordsKtTest : StringSpec() {

    init {
        "topKFrequent" {
            forAll(
                row(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 2, listOf("i", "love")),
                row(
                    arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"),
                    4,
                    listOf("the", "is", "sunny", "day")
                )
            ) { words, k, result ->
                topKFrequent(words, k) shouldBe result
            }
        }
    }

}
