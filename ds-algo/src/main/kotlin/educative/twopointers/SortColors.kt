package educative.twopointers

/* 14 Jul 2025 10:41 */

fun sortColors(arr: IntArray): IntArray {
  var start = 0
  var end = arr.lastIndex
  var current = start
  while (current < end) {
    when (arr[current]) {
      0 -> {
        arr.swap(start, current)
        start++
        current++
      }
      1 -> current++
      2 -> {
        arr.swap(current, end)
        end--
      }
    }
  }
  return arr
}

private fun IntArray.swap(index1: Int, index2: Int) {
  if (index1 != index2 && this[index1] != this[index2]) {
    this[index1] = this[index2].also { this[index2] = this[index1] }
  }
}
