package cci.recursion

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RobotInGridTest :
  StringSpec({
    "With all feasible cells" {
      val grid =
        arrayOf(
          booleanArrayOf(true, true, true),
          booleanArrayOf(true, true, true),
          booleanArrayOf(true, true, true),
        )
      findRoboPath(grid) shouldBe listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2), Pair(1, 2), Pair(2, 2))
    }

    "With some broken cells" {
      val grid =
        arrayOf(
          booleanArrayOf(true, true, true),
          booleanArrayOf(true, true, false),
          booleanArrayOf(true, true, true),
        )
      findRoboPath(grid) shouldBe listOf(Pair(0, 0), Pair(0, 1), Pair(1, 1), Pair(2, 1), Pair(2, 2))
    }

    "With no path" {
      val grid =
        arrayOf(
          booleanArrayOf(true, true, true),
          booleanArrayOf(true, true, false),
          booleanArrayOf(true, false, true),
        )
      findRoboPath(grid) shouldBe emptyList()
    }
  })
