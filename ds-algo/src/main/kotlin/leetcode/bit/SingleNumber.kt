package leetcode.bit

/* 26 Sep 2025 12:12 */

/** [136. Single Number](https://leetcode.com/problems/single-number) */
fun singleNumber(nums: IntArray): Int = nums.reduce { acc, num -> acc xor num }
