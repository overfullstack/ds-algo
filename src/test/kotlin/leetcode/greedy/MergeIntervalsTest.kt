package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MergeIntervalsTest : StringSpec({
    "mergeIntervals" {
        mergeIntervals(listOf(1 to 3, 2 to 6, 8 to 10, 15 to 18)) shouldBe listOf(
            1 to 6,
            8 to 10,
            15 to 18
        )
        mergeIntervals(listOf(1 to 4, 4 to 5)) shouldBe listOf(1 to 5)
    }
})
