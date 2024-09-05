package educative.array.slidingwindow

/* 04 Sep 2024 14:13 */

fun maxInSlidingWindow(arr: IntArray, k: Int): List<Int> {
  val queue = ArrayDeque<Int>()
  val result = mutableListOf<Int>()
  for (i in 0..k - 1) {
    while (queue.isNotEmpty() && arr[queue.last()] < arr[i]) {
      queue.removeLast()
    }
    queue.add(i)
  }
  result.add(arr[queue.first()])

  for (i in k..arr.lastIndex) {
    while (queue.isNotEmpty() && queue.first() < i - k + 1) {
      queue.removeFirst()
    }
    while (queue.isNotEmpty() && arr[queue.last()] < arr[i]) {
      queue.removeLast()
    }
    queue.add(i)
    result.add(arr[queue.first()])
  }
  return result
}
