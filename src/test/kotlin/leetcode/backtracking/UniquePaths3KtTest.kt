package leetcode.backtracking

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class UniquePaths3KtTest {
    @Test
    fun test1() {
        val grid = arrayOf(
            intArrayOf(1,0,0,0),
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,2,-1)
        )
        uniquePathsIII(grid) shouldBe 2
    }
}