package tbd;

import java.util.Arrays;
import java.util.Comparator;

public class MaximizeOrderVolume {

	public int maximizeOrderVolume(int[] startTimes, int[] durations, int[] orderVolumes) {
		int n = startTimes.length;

		// Create call objects for easier processing
		Call[] calls = new Call[n];
		for (int i = 0; i < n; i++) {
			calls[i] = new Call(startTimes[i], startTimes[i] + durations[i], orderVolumes[i]);
		}

		// Sort calls by end time for efficient processing
		Arrays.sort(calls, Comparator.comparingInt(a -> a.end));

		// dp[i] = maximum order volume achievable considering calls[0...i]
		int[] dp = new int[n];
		dp[0] = calls[0].volume; // First call can always be taken

		for (int i = 1; i < n; i++) {
			// Option 1: Don't take current call
			int notTake = dp[i - 1];

			// Option 2: Take current call + best possible from non-overlapping calls
			int take = calls[i].volume;

			// Find the latest non-overlapping call before current call
			int j = findLatestNonOverlapping(calls, i);
			if (j != -1) {
				take += dp[j];
			}

			dp[i] = Math.max(take, notTake);
		}

		return dp[n - 1];
	}

	// Find the latest call that doesn't overlap with calls[i]
	// Uses binary search since calls are sorted by end time

	// * Rightmost where condition is true
	private int findLatestNonOverlapping(Call[] calls, int currentIndex) {
		int left = 0;
		int right = currentIndex - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (calls[mid].end <= calls[currentIndex].start) { // ! Condition over left
				// This is a valid candidate, so discard everything to its left
				// as we are looking for the rightmost valid one
				left = mid + 1;
			} else {
				// This is not a valid candidate, so discard it and everything to its right
				right = mid - 1; // ! Coz right jumps over `mid` where condition was false
			}
		}
		// After the loop, `right` is the index of the rightmost element
		// for which `calls[mid].end <= calls[currentIndex].start` was true.
		return right; // ! return right
	}

	// Helper class to represent a call
	private static class Call {
		int start, end, volume;

		Call(int start, int end, int volume) {
			this.start = start;
			this.end = end;
			this.volume = volume;
		}
	}

	public static void main(String[] args) {
		MaximizeOrderVolume solution = new MaximizeOrderVolume();

		// Example 1: Simple case
		// Calls: [0-2: 5], [1-3: 3], [2-4: 4]
		// Optimal: Take calls 0 and 2 = 5 + 4 = 9
		System.out.println(
				solution.maximizeOrderVolume(
						new int[] {0, 1, 2}, new int[] {2, 2, 2}, new int[] {5, 3, 4})); // Expected: 9

		// Example 2: All calls overlap
		// Calls: [0-3: 2], [1-4: 3], [2-5: 4]
		// Optimal: Take highest volume call = 4
		System.out.println(
				solution.maximizeOrderVolume(
						new int[] {0, 1, 2}, new int[] {3, 3, 3}, new int[] {2, 3, 4})); // Expected: 4

		// Example 3: No overlaps
		// Calls: [0-1: 1], [2-3: 2], [4-5: 3]
		// Optimal: Take all calls = 1 + 2 + 3 = 6
		System.out.println(
				solution.maximizeOrderVolume(
						new int[] {0, 2, 4}, new int[] {1, 1, 1}, new int[] {1, 2, 3})); // Expected: 6

		System.out.println(
				solution.maximizeOrderVolume(
						new int[] {10, 5, 15, 18, 30},
						new int[] {30, 12, 20, 35, 35},
						new int[] {50, 51, 20, 25, 10})); // Expected: 76
	}
}
