package algoexpert.array

/** [704. Binary Search](https://leetcode.com/problems/binary-search/) */
fun IntArray.binarySearchRightmost(valueToSearch: Int): Int {
  var left = 0
  var right = lastIndex
  // * Rightmost where condition is true
  while (left <= right) {
    // ! `mid` calculation is left biased
    // ! so we have `<=` and `left = mid + 1` to let left cross right to avoid infinite loop
    val mid = left + (right - left) / 2
    when {
      // ! Left moves towards right when condition is true, so we end up at the Rightmost
      this[mid] <= valueToSearch -> left = mid + 1
      else -> right = mid - 1
    }
  }
  // After the loop, `right` is the rightmost index where `this[index] <= valueToSearch`
  // It either goes out of bounds on left or the value is not found
  return if (this[right] == valueToSearch) right else -1
}

fun IntArray.binarySearchLeftmost(valueToSearch: Int): Int {
  var left = 0
  var right = lastIndex
  // * Leftmost where condition is true
  while (left < right) {
    val mid = left + (right - left) / 2
    when {
      // ! Right moves towards left when condition is true, so we end up at the Leftmost
      this[mid] >= valueToSearch -> right = mid
      else -> left = mid + 1
    }
  }
  // After the loop, `right` is the leftmost index where `this[index] >= valueToSearch`
  // We must verify if this index equal to the value or greater than that
  return if (this[right] == valueToSearch) right else -1
}

fun main() {
  // * These searches handle duplicates also by returning right most index
  // * instead of instantly returning `mid` when match found
  println("Rightmost:")
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).binarySearchRightmost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightmost(7)) // 9
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightmost(10)) // -1
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchRightmost(0)) // -1

  println("\nLeftmost:")
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).binarySearchLeftmost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftmost(7)) // 6
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftmost(10)) // -1
  println(intArrayOf(1, 2, 3, 4, 5, 6, 7, 7, 7, 7).binarySearchLeftmost(0)) // -1
}
