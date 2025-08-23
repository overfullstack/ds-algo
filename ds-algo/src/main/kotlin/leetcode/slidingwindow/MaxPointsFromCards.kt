package leetcode.slidingwindow

/* 07 Aug 2025 10:29 */

fun maxScore(cardPoints: IntArray, k: Int): Int {
  val remainingK = cardPoints.size - k
  var sum = cardPoints.take(remainingK).sum()
  var minSum = sum
  for (i in remainingK..cardPoints.lastIndex) {
    sum -= cardPoints[i - remainingK]
    sum += cardPoints[i]
    minSum = minOf(minSum, sum)
  }
  return cardPoints.sum() - minSum
}

fun main() {
  println(maxScore(intArrayOf(1, 2, 3, 4, 5, 6, 1), 3))
  println(maxScore(intArrayOf(2, 2, 2), 2))
  println(maxScore(intArrayOf(9, 7, 7, 9, 7, 7, 9), 7))
}
