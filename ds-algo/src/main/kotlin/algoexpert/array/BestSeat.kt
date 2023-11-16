package algoexpert.array

fun bestSeat(seatArr: IntArray): Int {
  var maxZeroCount = Int.MIN_VALUE
  var maxZeroCountStartIndex: Int = -1
  var zeroCountStartIndex: Int = -1
  var i = 0
  while (i <= seatArr.lastIndex) {
    if (seatArr[i] == 0) {
      zeroCountStartIndex = i
      while (i <= seatArr.lastIndex && seatArr[i] == 0) {
        i++
      }
      if (i - zeroCountStartIndex > maxZeroCount) {
        maxZeroCountStartIndex = zeroCountStartIndex
        maxZeroCount = i - zeroCountStartIndex
      }
    }
    i++
  }
  return if (zeroCountStartIndex == -1) -1 else (maxZeroCountStartIndex + (maxZeroCount - 1) / 2)
}
