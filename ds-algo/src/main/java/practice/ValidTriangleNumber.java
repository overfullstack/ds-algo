package practice;

/* 26 Oct 2025 12:14 */

import java.util.Arrays;

import static java.lang.IO.println;

/**
 * [611. Valid Triangle Number](https://leetcode.com/problems/valid-triangle-number/)
 */
public class ValidTriangleNumber {
  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);
    var count = 0;
    // ! Fix the largest side (right) and find pairs of smaller sides
    for (var right = nums.length - 1; right >= 2; right--) {
      var left1 = 0;
      var left2 = right - 1;
      while (left1 < left2) {
        if (nums[left1] + nums[left2] > nums[right]) {
          // ! All elements between left1 and left2 form valid triangles with left2 and right
          count += left2 - left1;
          left2--;
        } else {
          left1++;
        }
      }
    }
    return count;
  }

  static void main() {
    var obj = new ValidTriangleNumber();
    println(obj.triangleNumber(new int[] {2, 2, 3, 4})); // 3
    println(obj.triangleNumber(new int[] {4, 2, 3, 4})); // 4
  }
}
