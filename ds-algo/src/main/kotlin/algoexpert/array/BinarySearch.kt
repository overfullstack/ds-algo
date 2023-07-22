package algoexpert.array

fun IntArray.binarySearch(valueToSearch: Int): Int {
  var left = 0
  var right = lastIndex
  while (left <= right) {
    val mid = (left + right) / 2
    when {
      valueToSearch < this[mid] -> right = mid - 1
      valueToSearch > this[mid] -> left = mid + 1
      else -> return mid
    }
  }
  return -1
}

tailrec fun IntArray.binarySearchRecursive(
    valueToSearch: Int,
    left: Int = 0,
    right: Int = lastIndex
): Int {
  if (left > right) {
    return -1
  }
  val mid = (left + right) / 2
  return when {
    valueToSearch < this[mid] -> binarySearchRecursive(valueToSearch, left, mid - 1)
    valueToSearch > this[mid] -> binarySearchRecursive(valueToSearch, mid + 1, right)
    else -> mid
  }
}
