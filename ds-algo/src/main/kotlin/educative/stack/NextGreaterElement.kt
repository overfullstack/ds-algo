package educative.stack

import java.util.Stack

/* 20 Jul 2025 08:01 */

fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
  val stk = Stack<Int>()
  val map = mutableMapOf<Int, Int>()
  for (num2 in nums2) {
    while (stk.isNotEmpty() && stk.peek() < num2) {
      map[stk.pop()] = num2
    }
    stk.push(num2)
  }
  while (stk.isNotEmpty()) {
    map[stk.pop()] = -1
  }
  return nums1.map { map[it]!! }.toIntArray()
}
