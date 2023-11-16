package algoexpert.array

fun IntArray.productArray(): IntArray {
  val leftToRightProduct = runningReduce { acc, item -> acc * item }
  val rightToLeftProduct = reversed().runningReduce { acc, item -> acc * item }.reversed()
  val result = IntArray(size)
  result[0] = rightToLeftProduct[1]
  result[result.lastIndex] = leftToRightProduct[leftToRightProduct.lastIndex - 1]
  for (i in 1 until result.lastIndex) {
    result[i] = leftToRightProduct[i - 1] * rightToLeftProduct[i + 1]
  }
  return result
}
