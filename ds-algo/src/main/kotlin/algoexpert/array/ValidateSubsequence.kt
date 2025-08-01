package algoexpert.array

fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
  var arrIndex = 0
  var seqIndex = 0
  while (arrIndex <= array.lastIndex && seqIndex <= sequence.lastIndex) {
    if (array[arrIndex] == sequence[seqIndex]) {
      seqIndex++
    }
    arrIndex++
  }
  return seqIndex == (sequence.lastIndex + 1)
}
