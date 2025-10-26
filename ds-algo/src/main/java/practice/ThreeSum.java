package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** [15. 3Sum](https://leetcode.com/problems/3sum/) */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		var result = new ArrayList<List<Integer>>();
		for (var left1 = 0; left1 < nums.length; left1++) {
			if (left1 == 0 || nums[left1] != nums[left1 - 1]) {
        // ! init left2, right in the inner loop
				var left2 = left1 + 1;
				var right = nums.length - 1; 
				while (left2 < right) {
					var sum = nums[left1] + nums[left2] + nums[right];
					if (sum > 0) {
						right--;
					} else if (sum < 0) {
						left2++;
					} else {
						result.add(Arrays.asList(nums[left1], nums[left2], nums[right]));
            do {
              left2++;
            } while (nums[left2] == nums[left2 - 1] && left2 < right);
					}
				}
			}
		}
		return result;
	}
}
