/* gakshintala created on 9/22/19 */
package leetcode.dp.burstballoons

/** [Burst Balloons](https://leetcode.com/problems/burst-balloons/) */
fun maxCoins(nums: IntArray): Int {
  val balloonCoins = arrayOf(1) + nums.toTypedArray() + arrayOf(1) // this is leetcode problem specific
  val table = Array(balloonCoins.size) { IntArray(balloonCoins.size) }
  // * Build from individual balloons to larger windows
  // * table[start][end] holds a non-zero value only if nums present are >= 3 (end - start + 1 >= 3)
  for (windowLen in 2..balloonCoins.lastIndex) {
    for ((wStart, wEnd) in (windowLen..balloonCoins.lastIndex).withIndex()) {
      table[wStart][wEnd] = // Max coins from this interval (wStart, wEnd)
        // Bursting which balloon last in this window give the max result
        (wStart + 1 until wEnd).maxOf { partition ->
          table[wStart][partition] + // Max coins from the left of the partition
            // Bursting this balloon after the left and right are optimally burst
            balloonCoins[wStart] * balloonCoins[partition] * balloonCoins[wEnd] + 
            table[partition][wEnd] // Max coins from the right of the partition
        }
    }
  }
  return table[0][balloonCoins.lastIndex]
}

fun main() {
  // val arr = readln().split(",").map { it.trim().toInt() }.toIntArray()
  val arr = intArrayOf(3, 1, 5, 8)
  println(maxCoins(arr))
}
