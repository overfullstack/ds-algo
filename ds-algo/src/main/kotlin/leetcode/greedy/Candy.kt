package leetcode.greedy

/* 03 Aug 2025 18:32 */

/** [135. Candy](https://leetcode.com/problems/candy/) */
fun candy(ratings: IntArray): Int {
  val candies = IntArray(ratings.size) { 1 }
  for (i in 1..ratings.lastIndex) {
    if (ratings[i - 1] < ratings[i]) {
      candies[i] = candies[i - 1] + 1
    }
  }
  for (i in (ratings.lastIndex - 1) downTo 0) {
    if (ratings[i + 1] < ratings[i]) {
      candies[i] = maxOf(candies[i], candies[i + 1] + 1)
    }
  }
  return candies.sum()
}
