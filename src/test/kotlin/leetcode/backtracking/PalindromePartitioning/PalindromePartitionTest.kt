package leetcode.backtracking.PalindromePartitioning

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class PalindromePartitionTest : StringSpec({
    "Palindrome Partition" {
        forAll(
            row("aab", listOf(listOf("aa", "b"), listOf("a", "a", "b")))
        ) { str, result ->
            palindromePartition(str) shouldContainExactlyInAnyOrder result
        }
    }
})
