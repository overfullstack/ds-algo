package leetcode.heap

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

/* 03 Aug 2025 14:07 */

class BusiestServersTest :
  StringSpec({
    "busiest Servers" {
      forAll(
        row(3, intArrayOf(1, 2, 3, 4, 5), intArrayOf(5, 2, 3, 3, 3), listOf(1)),
        row(3, intArrayOf(1, 2, 3, 4), intArrayOf(1, 2, 1, 2), listOf(0)),
        row(3, intArrayOf(1, 2, 3), intArrayOf(10, 12, 11), listOf(0, 1, 2)),
      ) { k, arrival, load, expected ->
        busiestServers(k, arrival, load) shouldBe expected
      }
    }
  })
