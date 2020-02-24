package leetcode.arrays

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class RemoveAdjacentDuplicatesKtTest : StringSpec() {

    init {
        "removeDuplicates" {
            forall(
                row("abbaca", "ca"),
                row("aaaaa", "a"),
                row("a", "a"),
                row("aabaa", "b")
            ) { s, result ->
                removeDuplicates(s) shouldBe result
            }
        }
        "removeKDuplicates" {
            forall(
                row("abcd", 2, "abcd"),
                row("deeedbbcccbdaa", 3, "aa"),
                row("pbbcggttciiippooaais", 2, "ps")
            ) { s, k, result ->
                removeKDuplicates(s, k) shouldBe result
            }
        }
    }

}
