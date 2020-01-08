/* gakshintala created on 12/24/19 */
package sorting

import kotlin.random.Random

fun findKthLargest(nums: IntArray, k: Int): Int {
    return findKthLargest(nums.toMutableList(), k)
}

fun findKthLargest(nums: MutableList<Int>, k: Int): Int {
    val randomPivotIndex = Random.nextInt(0, nums.size)
    val pivot = nums.removeAt(randomPivotIndex)
    val (smaller, larger) = nums.partition { it < pivot }
    return when {
        larger.size > k - 1 -> findKthLargest(larger.toMutableList(), k)
        larger.size < k - 1 -> findKthLargest(smaller.toMutableList(), k - larger.size - 1) // -1 discarding current pivot
        else -> pivot
    }
}

fun main() {
    val arr = readLine()!!.split(",").map { it.trim().toInt() }.toIntArray()
    print(findKthLargest(arr, readLine()!!.toInt()))
}