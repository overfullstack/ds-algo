/* gakshintala created on 1/24/20 */
package leetcode.greedy

import java.util.ArrayDeque

fun maxNumber(nums1: IntArray, nums2: IntArray, k: Int): IntArray =
  (maxOf(0, k - nums1.size)..minOf(k, nums2.size))
    .map { mergeMaxNums(maxNumberWithArr(nums1, k - it), maxNumberWithArr(nums2, it)) }
    .maxWithOrNull(Comparator { list1, list2 -> list1.compareTo(list2) })
    ?.toIntArray() ?: IntArray(0)

private tailrec operator fun List<Int>.compareTo(other: List<Int>): Int =
  when {
    this.isEmpty() && other.isEmpty() -> 0
    this.isEmpty() -> -1
    other.isEmpty() -> 1
    this.first() == other.first() -> this.drop(1).compareTo(other.drop(1))
    else -> this.first().compareTo(other.first())
  }

private tailrec fun mergeMaxNums(
  num1Digits: List<Int>,
  num2Digits: List<Int>,
  mergedDigits: List<Int> = emptyList(),
): List<Int> =
  when {
    num1Digits.isEmpty() -> mergedDigits + num2Digits
    num2Digits.isEmpty() -> mergedDigits + num1Digits
    num1Digits >= num2Digits ->
      mergeMaxNums(num1Digits.drop(1), num2Digits, mergedDigits + num1Digits.first())
    else -> mergeMaxNums(num1Digits, num2Digits.drop(1), mergedDigits + num2Digits.first())
  }

private fun maxNumberWithArr(arr: IntArray, resultLen: Int): List<Int> {
  val stk = ArrayDeque<Int>()
  for ((index, digit) in arr.withIndex()) {
    val remainingDigits = arr.lastIndex - index + 1
    while (stk.size + remainingDigits > resultLen && stk.isNotEmpty() && stk.peek() < digit) {
      stk.pop()
    }
    if (stk.size < resultLen) {
      stk.push(digit)
    }
  }
  return stk.reversed().toList()
}
