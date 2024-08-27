package educative.sorting.cyclicsort

/* 27 Aug 2024 15:25 */
fun missingNumber(arr: IntArray): Int {
  for (index in arr.indices) {
    while (arr[index] != 0 && index != arr[index] - 1) {
      arr.swap(index, arr[index] - 1)
    }
  }
  return arr.withIndex().firstOrNull { it.value == 0 }?.index?.plus(1) ?: 0
}
