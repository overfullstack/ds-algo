package leetcode.dp

/* 02 Aug 2025 14:08 */

/** [1105. Filling Bookcase Shelves](https://leetcode.com/problems/filling-bookcase-shelves/) */
fun minHeightShelvesTopDown(
  books: Array<IntArray>,
  shelfWidth: Int,
  idx: Int = 0,
  curMaxHeight: Int = 0,
  remainingWidth: Int = shelfWidth,
  memo: Array<IntArray> = Array(books.size) { IntArray(shelfWidth + 1) },
): Int =
  when {
    remainingWidth < 0 -> Int.MAX_VALUE // ! This should be before idx check
    idx == books.lastIndex + 1 -> curMaxHeight
    memo[idx][remainingWidth] != 0 -> memo[idx][remainingWidth]
    else -> {
      val (width, height) = books[idx]
      val sameRow =
        minHeightShelvesTopDown(
          books,
          shelfWidth,
          idx + 1,
          maxOf(curMaxHeight, height),
          remainingWidth - width,
          memo,
        )
      val nextRow =
        curMaxHeight +
          minHeightShelvesTopDown(books, shelfWidth, idx + 1, height, shelfWidth - width, memo)
      minOf(sameRow, nextRow).also { memo[idx][remainingWidth] = it }
    }
  }

fun minHeightShelvesBottomsUp(books: Array<IntArray>, shelfWidth: Int): Int {
  val dp = IntArray(books.size + 1)
  for (i in 1..books.lastIndex + 1) {
    val (newBookWidth, newBookHeight) = books[i - 1]
    dp[i] = dp[i - 1] + newBookHeight // Add new book to new Shelve row

    // * Move as many as previous books as possible to new row, check if they can reduce the `dp[i]`
    // * `dp[i]` is the minHeight formed with `i-1` books
    // ! `dp[i]` doesn't influence values of below it as
    // ! `dp[i-1]` holds minHeight formed and ith book is not in the picture
    // If you have enough capacity, all previous books can move to new shelve,
    // and `dp[j]` shall hit `dp[0]`, making `dp[i] = maxHeightForNewRow`,
    // which is max height of `i` books
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
  println(
    minHeightShelvesBottomsUp(
      arrayOf(
        intArrayOf(1, 1),
        intArrayOf(2, 3),
        intArrayOf(2, 3),
        intArrayOf(1, 1),
        intArrayOf(1, 1),
        intArrayOf(1, 1),
        intArrayOf(1, 2),
      ),
      4,
    )
  ) // 6
  println(
    minHeightShelvesBottomsUp(arrayOf(intArrayOf(1, 3), intArrayOf(2, 4), intArrayOf(3, 2)), 6)
  ) // 4

  println(
    minHeightShelvesTopDown(
      arrayOf(
        intArrayOf(1, 1),
        intArrayOf(2, 3),
        intArrayOf(2, 3),
        intArrayOf(1, 1),
        intArrayOf(1, 1),
        intArrayOf(1, 1),
        intArrayOf(1, 2),
      ),
      4,
    )
  ) // 6
  println(
    minHeightShelvesTopDown(arrayOf(intArrayOf(1, 3), intArrayOf(2, 4), intArrayOf(3, 2)), 6)
  ) // 4
  println(
    minHeightShelvesTopDown(
      arrayOf(intArrayOf(7, 3), intArrayOf(8, 7), intArrayOf(2, 7), intArrayOf(2, 5)),
      10,
    )
  ) // 15
}
