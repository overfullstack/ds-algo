package leetcode.greedy

fun canReach(arr: IntArray, start: Int): Boolean = // This is implicit DFS
if (start < 0 || start > arr.lastIndex || arr[start] < 0) {
    false
  } else {
    arr[start] = -arr[start] // Visited
    arr[start] == 0 || canReach(arr, start - arr[start]) || canReach(arr, start + arr[start])
  }
