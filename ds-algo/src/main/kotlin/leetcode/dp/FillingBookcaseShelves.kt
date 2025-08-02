package leetcode.dp


/* 02 Aug 2025 14:08 */

fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
  val dp = IntArray(books.size + 1)
  dp[0] = 0 // dp is one index ahead
  for (i in 1..(books.lastIndex + 1)) {
    val (newBookWidth, newBookHeight) = books[i - 1]
    dp[i] = dp[i - 1] + newBookHeight // Add new book to new row

    // Move as many as previous books as possible to new row, while keeping the height minimum
    // If you have enough capacity, all previous books can move to new shelve, 
    // and `dp[j]` shall hit `dp[0]`, making `dp[i] = maxHeightForNewRow`
    var j = i - 2 // Remaining books
    var widthForNewRow = newBookWidth
    var maxHeightForNewRow = newBookHeight
    while (j >= 0 && widthForNewRow + books[j][0] <= shelfWidth) {
      widthForNewRow += books[j][0]
      maxHeightForNewRow = maxOf(maxHeightForNewRow, books[j][1])
      // Leave the previous book or Move it to new row
      // `dp[i]` is the minHeight formed with `i-1` books
      dp[i] = minOf(dp[i], dp[j] + maxHeightForNewRow)
      j--
    }
  }
  return dp.last()
}

fun main() {
  minHeightShelves(arrayOf(intArrayOf(1, 1), intArrayOf(2, 3), intArrayOf(3, 4)), 4)
}
