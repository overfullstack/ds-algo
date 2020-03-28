package backtracking

import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Test

internal class CombinationsKtTest {

    @Test
    fun combinations1() {
        combinations("abc") shouldContainAll listOf("a", "b", "c", "ab", "ac", "bc", "abc")
    }
}
