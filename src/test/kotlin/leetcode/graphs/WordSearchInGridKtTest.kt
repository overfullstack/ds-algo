package leetcode.graphs

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import leetcode.backtracking.findWords
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class WordSearchInGridKtTest {

    @Test
    fun test1() {
        val board = arrayOf(
            charArrayOf('o', 'a', 'a', 'n'),
            charArrayOf('o', 'a', 'a', 'n'),
            charArrayOf('e', 't', 'a', 'e'),
            charArrayOf('i', 'h', 'k', 'r'),
            charArrayOf('i', 'f', 'l', 'v')
        )
        val words = arrayOf("oath", "pea", "eat", "rain")
        findWords(board, words) shouldContainExactlyInAnyOrder listOf("eat", "oath")
    }

    @Test
    fun test2() {
        val board = arrayOf(charArrayOf('a', 'a'))
        val words = arrayOf("a")
        Assertions.assertEquals(listOf("a"), findWords(board, words))
    }

    @Test
    fun test3() {
        val board = arrayOf(charArrayOf('a', 'b'), charArrayOf('a', 'a'))
        val words = arrayOf("aba", "aaa", "aaab", "baa", "baa", "aaba", "aaba")
        findWords(board, words) shouldContainExactlyInAnyOrder listOf("aaa", "aaab", "aaba", "aba", "baa")
    }
}
