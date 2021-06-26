package hackerrank.arrays

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class LargestPermutationWithKSwapsTest : StringSpec({
  "Largest permutation with k swaps" {
    forAll(
      row(2, arrayOf(2, 1, 3), arrayOf(3, 2, 1)),
      row(1, arrayOf(4, 2, 3, 5, 1), arrayOf(5, 2, 3, 4, 1)),
      row(2, arrayOf(4, 2, 3, 5, 1), arrayOf(5, 4, 3, 2, 1)),
      row(1, arrayOf(2, 1, 3), arrayOf(3, 1, 2)),
      row(1, arrayOf(2, 1), arrayOf(2, 1)),
    ) { k, arr, result ->
      largestPermutation(k, arr) shouldBe result
    }
  }
})
