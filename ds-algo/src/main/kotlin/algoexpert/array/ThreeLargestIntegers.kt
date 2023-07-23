package algoexpert.array

fun threeLargestIntegers(arr: IntArray): List<Int> {
  var max1 = Int.MIN_VALUE
  var max2 = Int.MIN_VALUE
  var max3 = Int.MIN_VALUE
  for (value in arr) {
    if (value > max1) {
      max3 = max2
      max2 = max1
      max1 = value
    } else if (value > max2) {
      max3 = max2
      max2 = value
    } else if (value > max3) {
      max3 = value
    }
  }
  return listOf(max3, max2, max1)
}
