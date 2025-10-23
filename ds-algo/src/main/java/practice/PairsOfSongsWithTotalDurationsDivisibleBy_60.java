package practice;

import java.util.HashMap;

/* 23 Oct 2025 11:16 */

/**
 * [1010. Pairs of Songs With Total Durations Divisible by
 * 60](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/)
 */
public class PairsOfSongsWithTotalDurationsDivisibleBy_60 {
	public int numPairsDivisibleBy60(int[] time) {
		var remainderToCount = new HashMap<Integer, Integer>();
		remainderToCount.merge(time[0] % 60, 1, Integer::sum);
		var result = 0;
		for (var i = 1; i < time.length; i++) {
			if (time[i] != 0) { // ! This is not needed for the problem as time[i] >= 1
				var remainder = time[i] % 60;
				if (remainder == 0) {
					result += remainderToCount.getOrDefault(0, 0);
				} else {
					result += remainderToCount.getOrDefault(60 - remainder, 0);
				}
				remainderToCount.merge(remainder, 1, Integer::sum);
			} else {
				remainderToCount.merge(0, 1, Integer::sum);
			}
		}
		return result;
	}

	static void main() {
		var pairsOfSongsWithTotalDurationsDivisibleBy_60 =
				new PairsOfSongsWithTotalDurationsDivisibleBy_60();
		System.out.println(
				pairsOfSongsWithTotalDurationsDivisibleBy_60.numPairsDivisibleBy60(
						new int[] {0, 0, 0})); // 0
		System.out.println(
				pairsOfSongsWithTotalDurationsDivisibleBy_60.numPairsDivisibleBy60(
						new int[] {30, 20, 150, 100, 40})); // 3
		System.out.println(
				pairsOfSongsWithTotalDurationsDivisibleBy_60.numPairsDivisibleBy60(
						new int[] {60, 60, 60})); // 3
	}
}
