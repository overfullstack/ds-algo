package educative.hashing

/* 29 Jul 2025 21:12 */

fun twoSum(nums: IntArray, target: Int): Pair<Int, Int>? {
  val map = mutableMapOf<Int, Int>()
  for ((index, num) in nums.withIndex()) {
    val diff = target - num
    if (map.contains(diff)) {
      return map[diff]!! to index
    }
    map[num] = index
  }
  return null
}
