package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SearchInRotatedSortedTest : StringSpec({
  "searchInRotatedSorted1" {
    search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0) shouldBe 4
  }
  "searchInRotatedSorted2" {
    search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3) shouldBe -1
  }
  "searchInRotatedSorted3" {
    search(intArrayOf(3, 1), 1) shouldBe 1
  }
})
