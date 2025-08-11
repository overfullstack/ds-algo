package leetcode.array

/* 11 Aug 2025 08:20 */

/**
 * [315. Count of Smaller Numbers After
 * Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self)
 */
fun countSmaller(nums: IntArray): List<Int> {
  val result = IntArray(nums.size)
  val numsWithIndex = nums.mapIndexed { index, num -> index to num }.toMutableList()
  merge(numsWithIndex, 0, nums.lastIndex, result)
  return result.toList()
}

fun merge(nums: MutableList<Pair<Int, Int>>, startIdx: Int, endIdx: Int, result: IntArray) {
  if (startIdx >= endIdx) return
  val mid = startIdx + (endIdx - startIdx) / 2
  merge(nums, startIdx, mid, result)
  merge(nums, mid + 1, endIdx, result)
  mergeSort(nums, startIdx, endIdx, mid, result)
}

fun mergeSort(
  nums: MutableList<Pair<Int, Int>>,
  startIdx: Int,
  endIdx: Int,
  mid: Int,
  result: IntArray,
) {
  var leftIdx = 0
  val left = nums.subList(startIdx, mid + 1).toMutableList()
  var rightIdx = 0
  val right = nums.subList(mid + 1, endIdx + 1).toMutableList()
  var currentIdx = startIdx
  var smallerOnRightCount = 0
  while (leftIdx <= left.lastIndex && rightIdx <= right.lastIndex) {
    when {
      left[leftIdx].second <= right[rightIdx].second -> {
        result[left[leftIdx].first] += smallerOnRightCount
        nums[currentIdx] = left[leftIdx]
        leftIdx++
      }
      else -> {
        smallerOnRightCount++
        nums[currentIdx] = right[rightIdx]
        rightIdx++
      }
    }
    currentIdx++
  }

  while (leftIdx <= left.lastIndex) {
    result[left[leftIdx].first] += smallerOnRightCount
    nums[currentIdx] = left[leftIdx]
    leftIdx++
    currentIdx++
  }
  while (rightIdx <= right.lastIndex) {
    nums[currentIdx] = right[rightIdx]
    rightIdx++
    currentIdx++
  }
}

// ! Descending sort
fun mergeSortDescending(
  nums: MutableList<Pair<Int, Int>>,
  startIdx: Int,
  endIdx: Int,
  mid: Int,
  result: IntArray,
) {
  val left = nums.subList(startIdx, mid + 1).toMutableList()
  val right = nums.subList(mid + 1, endIdx + 1).toMutableList()
  var leftIdx = 0
  var rightIdx = 0
  var currentIdx = startIdx

  while (leftIdx < left.size && rightIdx < right.size) {
    when {
      left[leftIdx].second > right[rightIdx].second -> {
        // left element is greater than right element
        // All remaining elements in right (from rightIdx onwards) are smaller than left[leftIdx]
        // ! `+=` happens only once in a recursive call for an idx as the leftIdx moves on.
        // This is more for further iterations
        result[left[leftIdx].first] += (right.lastIndex - rightIdx + 1)
        nums[currentIdx] = left[leftIdx]
        leftIdx++
      }
      else -> {
        // right element is greater or equal
        nums[currentIdx] = right[rightIdx]
        rightIdx++
      }
    }
    currentIdx++
  }

  while (leftIdx < left.size) {
    nums[currentIdx] = left[leftIdx]
    leftIdx++
    currentIdx++
  }
  while (rightIdx < right.size) {
    nums[currentIdx] = right[rightIdx]
    rightIdx++
    currentIdx++
  }
}

fun main() {
  println(countSmaller(intArrayOf(5, 2, 6, 1)))
}
