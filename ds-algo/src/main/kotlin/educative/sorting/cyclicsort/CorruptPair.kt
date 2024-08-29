package educative.sorting.cyclicsort

/* 27 Aug 2024 17:30 */

fun corruptPair(arr: IntArray): Pair<Int, Int> {
  for (index in arr.indices) {
    while (arr[index] != index + 1 && arr[index] != arr[arr[index] - 1]) {
      arr.swap(index, arr[index] - 1)
    }
  }
  val corruptPair = arr.withIndex().first { it.value != it.index + 1 }
  return corruptPair.index + 1 to corruptPair.value
}
