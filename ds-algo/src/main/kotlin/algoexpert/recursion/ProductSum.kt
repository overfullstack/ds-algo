package algoexpert.recursion

fun productSum(splArr: List<Any>, depth: Int = 1): Int {
  var sum = 0
  for (i in splArr) {
    @Suppress("UNCHECKED_CAST")
    sum +=
      depth *
        when {
          i is Int -> i
          else -> productSum(i as List<Any>, depth + 1)
        }
  }
  return sum
}
