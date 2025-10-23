package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** [15. 3Sum](https://leetcode.com/problems/3sum/) */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		var result = new ArrayList<List<Integer>>();
		for (var i = 0; i < nums.length; i++) {
			if (i == 0 || nums[i] != nums[i - 1]) {
				var j = i + 1;
				var k = nums.length - 1;
				while (j < k) {
					var sum = nums[i] + nums[j] + nums[k];
					if (sum > 0) {
						k--;
					} else if (sum < 0) {
						j++;
					} else {
						result.add(Arrays.asList(nums[i], nums[j], nums[k]));
						do {
							j++;
						} while (nums[j] == nums[j - 1] && j < k);
					}
				}
			}
		}
		return result;
	}
}
