package leetcode.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe
import io.kotest.data.row

class RemoveAdjacentDuplicatesKtTest : StringSpec() {

    init {
        "removeDuplicates" {
            forAll(
                row("abbaca", "ca"),
                row("aaaaa", "a"),
                row("a", "a"),
                row("aabaa", "b")
            ) { s, result ->
                removeDuplicates(s) shouldBe result
            }
        }
        "removeKDuplicates" {
            forAll(
                row("abcd", 2, "abcd"),
                row("deeedbbcccbdaa", 3, "aa"),
                row("pbbcggttciiippooaais", 2, "ps")
            ) { s, k, result ->
                removeKDuplicates(s, k) shouldBe result
            }
        }
    }

}
