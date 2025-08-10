package leetcode.dp

import java.util.TreeMap

/* 09 Aug 2025 23:47 */
/**
 * [975. Odd Even Jump](https://leetcode.com/problems/odd-even-jump) This problem description is not
 * clear, but it expects you to jump alternatively, and it expects only indices with odd jumps
 */
fun oddEvenJumps(arr: IntArray): Int {
  if (arr.size <= 1) return arr.size
  val canJump = Array(arr.size) { BooleanArray(2) }
  canJump[arr.lastIndex][0] = true
  canJump[arr.lastIndex][1] = true

  val valueToIndex = TreeMap<Int, Int>()
  valueToIndex[arr.last()] = arr.lastIndex
  for (i in arr.lastIndex - 1 downTo 0) {
    val currentValue = arr[i]
    valueToIndex.ceilingEntry(currentValue)?.let { canJump[i][1] = canJump[it.value][0] }
    valueToIndex.floorEntry(currentValue)?.let { canJump[i][0] = canJump[it.value][1] }

    valueToIndex[arr[i]] = i
  }

  return canJump.count { it[1] }
}

fun main() {
  println(oddEvenJumps(intArrayOf(10, 13, 12, 14, 15)))
  println(oddEvenJumps(intArrayOf(2, 3, 1, 1, 4))) // 3
}
