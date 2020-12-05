package backtracking

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import org.junit.jupiter.api.Test

internal class CombinationsTest {

    @Test
    fun combinations1() {
        combinations("abc") shouldContainExactlyInAnyOrder listOf("a", "b", "c", "ab", "ac", "bc", "abc")
        combinations("abcd") shouldContainExactlyInAnyOrder listOf(
            "a",
            "ab",
            "abc",
            "abcd",
            "abd",
            "ac",
            "acd",
            "ad",
            "b",
            "bc",
            "bcd",
            "bd",
            "c",
            "cd",
            "d"
        )
    }
}
