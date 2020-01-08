/* gakshintala created on 1/3/20 */
package leetcode.arrays

fun productExceptSelf(nums: IntArray): IntArray {
    if (nums.size < 2) {
        return intArrayOf(0)
    }
    val product = IntArray(nums.size)
    
    product[0] = 1
    product[1] = nums[0]
    for (left in 2..nums.lastIndex) {
        product[left] = product[left - 1] * nums[left - 1]
    }

    var rightProduct = 1
    for (right in nums.lastIndex - 1 downTo 0) {
        rightProduct *= nums[right + 1]
        product[right] *= rightProduct
    }

    return product
}