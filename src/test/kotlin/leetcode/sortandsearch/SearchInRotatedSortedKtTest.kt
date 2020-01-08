package leetcode.sortandsearch

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class SearchInRotatedSortedKtTest : StringSpec() {

    init {
        "searchInRotatedSorted1" { 
            searchInRotatedSorted(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0) shouldBe 4
        }
        "searchInRotatedSorted2" {
            searchInRotatedSorted(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3) shouldBe -1
        }
        "searchInRotatedSorted3" {
            searchInRotatedSorted(intArrayOf(3, 1), 1) shouldBe 1
        }
    }

}