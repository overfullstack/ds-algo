package hackerrank.arrays

fun newYearChaos(array: IntArray) =
  array
    .withIndex()
    .fold(0) { swaps, curEle ->
      if ((curEle.value - (curEle.index + 1)) > 2) {
        return "Too chaotic"
      }
      swaps + (maxOf(0, curEle.value - 2) until curEle.index).count { array[it] > curEle.value }
    }
    .toString()

fun main() {
  val noOfTests = readLine()!!.toInt()
  repeat(noOfTests) {
    readLine()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    println(newYearChaos(arr))
  }
}
