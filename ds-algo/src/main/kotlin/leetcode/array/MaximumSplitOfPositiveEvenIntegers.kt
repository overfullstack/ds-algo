package leetcode.array

/* 04 Aug 2025 21:54 */

fun maximumEvenSplit(finalSum: Long): List<Long> {
  if (finalSum % 2 != 0L) return emptyList()
  var i = 2L
  var sum = 0L
  val ans = mutableListOf<Long>()
  while (sum + i <= finalSum) {
    sum += i
    ans.add(i)
    i += 2
  }
  ans[ans.lastIndex] += finalSum - sum
  return ans
}

fun main() {
  println(maximumEvenSplit(12))
}
