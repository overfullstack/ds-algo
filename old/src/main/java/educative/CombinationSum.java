package educative;

import java.util.*;

class CombinationSum {
	public static List<List<Integer>> combinationSum(int[] nums, int target) {
		// Initialize dp
		List<List<List<Integer>>> dp = new ArrayList<>(target + 1);
		dp.add(new ArrayList<>());
		dp.get(0).add(new ArrayList<>());

		// For each value from 1 to target
		for (int i = 1; i <= target; i++) {
			dp.add(new ArrayList<>());
			for (int num : nums) {
				if (num <= i) {
					// Checking previous results from dp
					for (List<Integer> prev : dp.get(i - num)) {
						List<Integer> temp = new ArrayList<>(prev);
						temp.add(num);
						temp.sort(null);

						// If the new combination is not already in dp
						if (!dp.get(i).contains(temp)) {
							dp.get(i).add(temp);
						}
					}
				}
			}
		}

		// Return the combinations
		return dp.get(target);
	}

	// Driver code
	public static void main(String[] args) {
		int[][] nums = {
			{3, 6, 7, 8},
			{2, 3, 5},
			{4, 5, 6, 9},
			{20, 25, 30, 35, 40},
			{3, 5, 7}
		};
		int[] targets = {15, 5, 11, 40, 15};

		for (int i = 0; i < nums.length; i++) {
			System.out.println((i + 1) + ". \tnums: " + Arrays.toString(nums[i]));
			System.out.println("\tTarget: " + targets[i]);

			List<List<Integer>> combinations = combinationSum(nums[i], targets[i]);

			System.out.println("\tCombinations: " + combinations);
			System.out.println(String.join("", Collections.nCopies(100, "-")));
		}
	}
}
