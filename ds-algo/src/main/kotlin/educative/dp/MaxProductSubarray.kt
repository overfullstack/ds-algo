package educative.dp

/* 26 Jul 2025 13:13 */

fun maxProductSubarray(arr: IntArray): Int {
  var maxSoFar = arr[0]
  var minSoFar = arr[0]
  var result = maxSoFar

  // * Beware of `0`s and `-ve`s
  for (i in 1..arr.lastIndex) {
    val tempMaxSoFar = maxOf(arr[i], maxSoFar * arr[i], minSoFar * arr[i])
    minSoFar = minOf(arr[i], maxSoFar * arr[i], minSoFar * arr[i])
    maxSoFar = tempMaxSoFar
    result = maxOf(result, maxSoFar)
  }
  return result
}
