package backtracking

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.specs.StringSpec

class PermutationsKtTest : StringSpec() {

    init {
        "permutations" {
            permute(intArrayOf(1, 2, 3)) shouldContainExactlyInAnyOrder listOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1)
            )
        }
    }

}