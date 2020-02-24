package leetcode.arrays

import io.kotlintest.data.suspend.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class TopKFrequentWordsKtTest : StringSpec() {

    init {
        "topKFrequent" {
            forall(
                row(arrayOf("i", "love", "leetcode", "i", "love", "coding"), 2, listOf("i", "love")),
                row(arrayOf("the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"), 4, listOf("the", "is", "sunny", "day"))
            ) {words, k, result ->
                topKFrequent(words, k) shouldBe result
            }
        }
    }

}
