package algoexpert.array

/** [704. Binary Search](https://leetcode.com/problems/binary-search/) */
fun IntArray.binarySearchRightMost(valueToSearch: Int): Int {
  var left = 0
  var right = lastIndex
  // * Rightmost where condition is true
  while (left <= right) {
    val mid = (left + right) / 2
    when {
      // * This handles duplicates also by returning right most index
      // * instead of instantly returning `mid` when match found
      this[mid] <= valueToSearch -> left = mid + 1 // ! Left moves towards right
      else -> right = mid - 1
    }
  }
  // After the loop, `right` is the rightmost index where `this[index] <= valueToSearch`
  // It either goes out of bounds on left or the value is not found
  return if (right < 0 || this[right] != valueToSearch) -1 else right
}

fun IntArray.binarySearchLeftMost(valueToSearch: Int): Int {
  var left = 0
  var right = lastIndex
  // * Leftmost where condition is true
  while (left < right) {
    val mid = left + (right - left) / 2
    when {
      this[mid] >= valueToSearch -> right = mid // ! Right moves towards left
      else -> left = mid + 1
    }
  }
  // After the loop, `left` is the leftmost index where `this[index] >= valueToSearch`
  // We must verify if this index actually contains the value.
  return if (this[right] == valueToSearch) right else -1
}

fun main() {
  println("Rightmost:")
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).binarySearchRightMost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightMost(7)) // 9
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightMost(10)) // -1
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightMost(0)) // -1

  println("\nLeftmost:")
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).binarySearchLeftMost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftMost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftMost(10)) // -1
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftMost(0)) // -1
}
