package educative.sorting.cyclicsort

/* 27 Aug 2024 13:12 */
fun cyclicSort(arr: IntArray): IntArray {
  for (index in arr.indices) {
    while (index != arr[index] - 1) {
      arr.swap(index, arr[index] - 1)
    }
  }
  return arr
}

fun IntArray.swap(index1: Int, index2: Int) {
  val temp = this[index1]
  this[index1] = this[index2]
  this[index2] = temp
}
