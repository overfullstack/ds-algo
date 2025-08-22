package leetcode.graph

import kotlin.math.abs

/* 22 Aug 2025 08:19 */

/** [818. Race Car](https://leetcode.com/problems/race-car/) */
fun racecar(target: Int): Int {
  val queue = ArrayDeque<Triple<Int, Int, Int>>()
  val visited = mutableSetOf<Pair<Int, Int>>()
  queue.add(Triple(0, 1, 0))
  while (queue.isNotEmpty()) {
    val (pos, speed, instructionCount) = queue.removeFirst()
    if (pos == target) {
      return instructionCount
    }
    visited += (pos to speed)

    val newPosWithA = pos + speed
    val newSpeedWithA = speed * 2
    // ! Prune so we don't go too far
    if ((newPosWithA to newSpeedWithA) !in visited && abs(newPosWithA - target) < target) {
      queue.add(Triple(newPosWithA, newSpeedWithA, instructionCount + 1))
    }

    val newSpeedWithR = if (speed > 0) -1 else 1
    if ((pos to newSpeedWithR) !in visited) {
      queue.add(Triple(pos, newSpeedWithR, instructionCount + 1))
    }
  }
  return -1
}

fun main() {
  println(racecar(3)) // 2
  println(racecar(6)) // 5
}
