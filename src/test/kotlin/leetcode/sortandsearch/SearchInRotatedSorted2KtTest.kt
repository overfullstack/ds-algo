package leetcode.sortandsearch

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class SearchInRotatedSorted2KtTest : StringSpec() {

    init {
        "searchInRotatedSorted2" {
            searchInRotatedSorted2(
                intArrayOf(2, 5, 6, 0, 0, 1, 2),
                0
            ) shouldBe true
        }
        "searchInRotatedSorted22" {
            searchInRotatedSorted2(
                intArrayOf(2, 5, 6, 0, 0, 1, 2),
                3
            ) shouldBe false
        }
        "searchInRotatedSorted23" {
            searchInRotatedSorted2(intArrayOf(1, 3, 1, 1, 1), 3) shouldBe true
        }
    }

}
