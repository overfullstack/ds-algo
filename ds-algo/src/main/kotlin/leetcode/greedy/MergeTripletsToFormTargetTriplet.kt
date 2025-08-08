package leetcode.greedy

/* 08 Aug 2025 11:16 */

fun mergeTriplets(triplets: Array<IntArray>, target: IntArray): Boolean =
  triplets
    .filter { it[0] <= target[0] && it[1] <= target[1] && it[2] <= target[2] }
    .fold(intArrayOf(0, 0, 0)) { result, triplet ->
      intArrayOf(
        maxOf(result[0], triplet[0]),
        maxOf(result[1], triplet[1]),
        maxOf(result[2], triplet[2]),
      )
    }
    .contentEquals(target)

fun main() {
  println(
    mergeTriplets(
      arrayOf(intArrayOf(2, 5, 3), intArrayOf(1, 8, 4), intArrayOf(1, 7, 5)),
      intArrayOf(2, 7, 5),
    )
  )
  println(mergeTriplets(arrayOf(intArrayOf(3, 4, 5), intArrayOf(4, 5, 6)), intArrayOf(3, 2, 5)))
  println(
    mergeTriplets(
      arrayOf(intArrayOf(2, 5, 3), intArrayOf(2, 3, 4), intArrayOf(1, 2, 5), intArrayOf(5, 2, 3)),
      intArrayOf(5, 5, 5),
    )
  )
}
