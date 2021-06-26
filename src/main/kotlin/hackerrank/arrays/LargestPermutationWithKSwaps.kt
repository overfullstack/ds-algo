package hackerrank.arrays

fun largestPermutation(k: Int, arr: Array<Int>): Array<Int> {
  var swaps = k
  val buckets = IntArray(arr.size + 1)
  for ((digitIndex, digitValue) in arr.withIndex()) { // value to index map
    buckets[digitValue] = digitIndex
  }

  for (i in arr.indices) { // Highest valued digit should be swapped first.
    val nextHighestValue = arr.size - i
    val nextHighestValueIndex = buckets[nextHighestValue]
    if (nextHighestValueIndex != i) {
      buckets[nextHighestValue] = i
      buckets[arr[i]] = nextHighestValueIndex
      arr[i] = arr[nextHighestValueIndex].also { arr[nextHighestValueIndex] = arr[i] }
      swaps--
      if (swaps == 0) {
        return arr
      }
    }
  }
  return arr
}
