package leetcode.greedy

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainExactly

class SkylineTest :
  StringSpec({
    "Skyline" {
      forAll(
        row(
          arrayOf(
            intArrayOf(2, 9, 10),
            intArrayOf(3, 7, 15),
            intArrayOf(5, 12, 12),
            intArrayOf(15, 20, 10),
            intArrayOf(19, 24, 8)
          ),
          listOf(
            listOf(2, 10),
            listOf(3, 15),
            listOf(7, 12),
            listOf(12, 0),
            listOf(15, 10),
            listOf(20, 8),
            listOf(24, 0)
          )
        )
      ) { buildings, results ->
        getSkyline(buildings).forEachIndexed { i, result -> result shouldContainExactly results[i] }
      }
    }
  })
