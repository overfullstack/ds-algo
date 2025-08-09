package leetcode.greedy

/* 08 Aug 2025 08:22 */

/** [846. Hand of Straights](https://leetcode.com/problems/hand-of-straights/) */
fun isNStraightHand(hand: IntArray, groupSize: Int): Boolean {
  // ! Sorted Map
  val digitToCount = hand.groupBy { it }.mapValues { it.value.size }.toSortedMap()
  return digitToCount
    .asSequence()
    // ! Some larger digits may never pass this filter, as a smaller digit has decremented its freq
    .filter { it.value > 0 }
    .all { (digit, freq) ->
      (1 until groupSize).all { offset ->
        digitToCount[digit + offset]
          ?.let { it >= freq }
          // ! Only change the higher digit freq, ignore smaller digit freq as we move on anyway
          ?.also { digitToCount.computeIfPresent(digit + offset) { _, v -> v - freq } } ?: false
      }
    }
}

fun main() {
  println(isNStraightHand(intArrayOf(1, 2, 3, 6, 2, 3, 4, 7, 8), 3))
  println(isNStraightHand(intArrayOf(1, 2, 3, 4, 5), 4))
}
