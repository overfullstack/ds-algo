package leetcode.dp

import java.util.TreeMap

/* 09 Aug 2025 23:47 */
/** [975. Odd Even Jump](https://leetcode.com/problems/odd-even-jump) */
fun oddEvenJumps(arr: IntArray): Int {
  if (arr.size <= 1) return arr.size
  val canReachEnd = Array(arr.size) { BooleanArray(2) }
  canReachEnd[arr.lastIndex][0] = true // ! even
  canReachEnd[arr.lastIndex][1] = true // ! odd

  val valueToIdx = TreeMap<Int, Int>()
  valueToIdx[arr.last()] = arr.lastIndex
  // ! If reached to current Idx via even jump, record if the next index value hodling the ceiling
  // ! can reach to end via odd jump. vice versa
  for (i in arr.lastIndex - 1 downTo 0) {
    val currentValue = arr[i]
    valueToIdx.ceilingEntry(currentValue)?.let { canReachEnd[i][1] = canReachEnd[it.value][0] }
    valueToIdx.floorEntry(currentValue)?.let { canReachEnd[i][0] = canReachEnd[it.value][1] }
    valueToIdx[currentValue] = i
  }
  // ! Since 1st jump is odd, only counting odd. Any odd works (1st, 3rd...)
  return canReachEnd.count { it[1] }
}

fun main() {
  println(oddEvenJumps(intArrayOf(10, 13, 12, 14, 15))) // 2
  println(oddEvenJumps(intArrayOf(2, 3, 1, 1, 4))) // 3
}
