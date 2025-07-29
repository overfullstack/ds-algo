package educative.twopointers

/* 28 Jul 2025 11:44 */

fun findSumOfThree(nums: IntArray, target: Int): Boolean {
  var left = 0
  var right = nums.lastIndex
  for (num in nums) {
    while (left < right) {
      val sum = nums[left] + num + nums[right]
      when {
        sum == target -> return true
        sum > target -> right--
        else -> left++
      }
    }
  }
  return false
}
