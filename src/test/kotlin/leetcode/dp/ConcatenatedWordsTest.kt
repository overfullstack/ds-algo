package leetcode.dp

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class ConcatenatedWordsTest : StringSpec({
    "Concatenated words" {
        forAll(
            row(
                arrayOf("cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"),
                listOf("catsdogcats", "dogcatsdog", "ratcatdogcat")
            ),
        ) { words, result ->
            findAllConcatenatedWordsInADict(words) shouldContainExactlyInAnyOrder result
        }
    }
})
