package educative.sorting.cyclicsort

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CyclicSortTest :
  StringSpec({
    "cyclic sort" {
      forAll(row(intArrayOf(3, 1, 2, 5, 4), intArrayOf(1, 2, 3, 4, 5))) { input, result ->
        cyclicSort(input) shouldBe result
      }
    }
  })
