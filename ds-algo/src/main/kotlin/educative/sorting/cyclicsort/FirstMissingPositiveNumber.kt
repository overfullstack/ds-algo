package educative.sorting.cyclicsort

/* 27 Aug 2024 22:12 */
fun firstMissingPositiveNumber(arr: IntArray): Int {
  for (index in arr.indices) {
    while (arr[index] != index + 1 && arr[index] in (1..arr.size)) {
      arr.swap(index, arr[index] - 1)
    }
  }
  return (arr.withIndex().firstOrNull { it.value != it.index + 1 }?.index ?: arr.size) + 1
}
