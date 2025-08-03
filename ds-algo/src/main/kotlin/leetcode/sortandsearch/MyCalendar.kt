package leetcode.sortandsearch

import java.util.TreeMap

/* 03 Aug 2025 18:02 */

class MyCalendar {
  val calendar = TreeMap<Int, Int>()

  fun book(startTime: Int, endTime: Int): Boolean {
    val prev = calendar.floorEntry(startTime)
    val next = calendar.ceilingEntry(startTime)
    return when {
      (prev == null || prev.value <= startTime) && (next == null || endTime <= next.key) -> {
        calendar[startTime] = endTime
        true
      }
      else -> false
    }
  }
}
