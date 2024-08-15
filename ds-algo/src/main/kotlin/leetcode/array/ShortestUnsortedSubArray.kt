package leetcode.array

fun shortestUnsortedSubArray(arr: IntArray): Int {
  var start = -1
  var end = -2
  var max = arr.first()
  var min = arr.last()

  for (i in 1..arr.lastIndex) {
    max = maxOf(max, arr[i])
    if (arr[i] < max) end = i // As all elements on right should be greater than middle + left

    min = minOf(min, arr[arr.lastIndex - i])
    if (arr[arr.lastIndex - i] > min)
      start = arr.lastIndex - i // As all elements on left should be less than right + middle
  }
  return end - start + 1
}
