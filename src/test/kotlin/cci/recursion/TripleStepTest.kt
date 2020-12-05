package cci.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class TripleStepTest : StringSpec({
    "Triple Step" {
        forAll(
            row(0, 1),
            row(1, 1),
            row(2, 2),
            row(3, 4),
            row(4, 7),
            row(5, 13)
        ) { stairCount, result ->
            possibleWaysToRunUpStairs(stairCount) shouldBe result
        }
    }
})
