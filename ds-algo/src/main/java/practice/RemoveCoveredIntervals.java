package practice;

/* 05 Sep 2025 13:29 */

import java.util.Arrays;
import java.util.Comparator;

/** [1288. Remove Covered Intervals](https://leetcode.com/problems/remove-covered-intervals/) */
public class RemoveCoveredIntervals {
	public int removeCoveredIntervals(int[][] intervals) {
		Arrays.sort( // ! Sort by start and descending end to pick the interval that covers others
				intervals,
				Comparator.<int[]>comparingInt(interval -> interval[0])
						.thenComparing(Comparator.<int[]>comparingInt(interval -> interval[1]).reversed()));
		var coveredIntervals = 0;
		var curEnd = intervals[0][1];
		for (var i = 1; i < intervals.length; i++) {
			if (intervals[i][1] <= curEnd) {
				coveredIntervals++;
			} else {
				curEnd = intervals[i][1];
			}
		}
		return intervals.length - coveredIntervals;
	}

	static void main() {
		var removeCoveredIntervals = new RemoveCoveredIntervals();
		System.out.println(
				removeCoveredIntervals.removeCoveredIntervals(new int[][] {{1, 2}, {1, 4}, {3, 4}})); // 1
		System.out.println(
				removeCoveredIntervals.removeCoveredIntervals(new int[][] {{1, 4}, {3, 6}, {2, 8}})); // 2
		System.out.println(
				removeCoveredIntervals.removeCoveredIntervals(new int[][] {{1, 4}, {2, 3}})); // 1
	}
}
