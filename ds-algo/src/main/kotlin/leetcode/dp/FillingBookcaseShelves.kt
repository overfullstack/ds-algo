package leetcode.dp

/* 02 Aug 2025 14:08 */

fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
  val dp = IntArray(books.size + 1)
  for (i in 1..(books.lastIndex + 1)) {
    val (newBookWidth, newBookHeight) = books[i - 1]
    dp[i] = dp[i - 1] + newBookHeight // Add new book to new row

    // * Move as many as previous books as possible to new row, check if they can reduce the `dp[i]`
    // * `dp[i]` is the minHeight formed with `i-1` books
    // ! `dp[i]` doesn't influence values of below it as
    // ! `dp[i-1]` holds minHeight formed and ith book is not in the picture
    // If you have enough capacity, all previous books can move to new shelve,
    // and `dp[j]` shall hit `dp[0]`, making `dp[i] = maxHeightForNewRow`
    var j = i - 2 // Remaining books
    var widthForNewRow = newBookWidth
    var maxHeightForNewRow = newBookHeight
    while (j >= 0 && widthForNewRow + books[j][0] <= shelfWidth) {
      widthForNewRow += books[j][0]
      // ! Recalculating maxHeight for the new row from previous books
      maxHeightForNewRow = maxOf(maxHeightForNewRow, books[j][1])
      // Placing ith to (j+1)th books on top of j books, so `dp[j] + maxHeightForNewRow`
      dp[i] = minOf(dp[i], dp[j] + maxHeightForNewRow)
      j--
    }
  }
  return dp.last()
}

fun main() {
  minHeightShelves(arrayOf(intArrayOf(1, 1), intArrayOf(2, 3), intArrayOf(3, 4)), 4)
}
