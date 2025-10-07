package practice;

import java.util.Arrays;
import java.util.Comparator;

/* 03 Sep 2025 21:18 */

/**
 * [3169. Count Days Without Meetings](https://leetcode.com/problems/count-days-without-meetings/)
 */
public class CountDaysWithoutMeetings {
	public int countDays(int days, int[][] meetings) {
		Arrays.sort(meetings, Comparator.<int[]>comparingInt(m -> m[0]));
		var dayCount = meetings[0][0] - 1;
		var curEnd = meetings[0][1];
		for (var i = 1; i < meetings.length; i++) {
			dayCount += Math.max(0, meetings[i][0] - curEnd - 1);
			curEnd = Math.max(curEnd, meetings[i][1]);
		}
		dayCount += days - curEnd;
		return dayCount;
	}

	static void main() {
		var sol = new CountDaysWithoutMeetings();
		System.out.println(sol.countDays(10, new int[][] {{5, 7}, {1, 3}, {9, 10}})); // 2
		System.out.println(sol.countDays(5, new int[][] {{2, 4}, {1, 3}})); // 1
		System.out.println(sol.countDays(6, new int[][] {{1, 6}})); // 0
	}
}
