package practice.backtracking;

import static java.lang.IO.println;

import java.util.Arrays;
import java.util.stream.IntStream;

/* 18 Oct 2025 14:16 */

/** [473. Matchsticks to Square](https://leetcode.com/problems/matchsticks-to-square/) */
public class MatchsticksToSquare {
	public boolean makesquare(int[] matchsticks) {
		final var sum = Arrays.stream(matchsticks).sum();
		if (sum % 4 != 0) {
			return false;
		}
		// ! Sorting in descending order improves perf as we quickly reach invalid path
		Arrays.sort(matchsticks);
		IntStream.range(0, matchsticks.length - 1)
				.forEach(
						i -> {
							var temp = matchsticks[i];
							matchsticks[i] = matchsticks[matchsticks.length - 1 - i];
							matchsticks[matchsticks.length - 1 - i] = temp;
						});
		return solve(0, new int[4], sum / 4, matchsticks);
	}

	private static boolean solve(int idx, int[] sides, int sideLen, int[] matchsticks) {
		if (idx == matchsticks.length) {
			return allSidesEqual(sides);
		}
		for (var i = 0; i < 4; i++) {
			if (sides[i] + matchsticks[idx] <= sideLen) {
				sides[i] += matchsticks[idx];
				final var result = solve(idx + 1, sides, sideLen, matchsticks);
				if (result) {
					return true;
				}
				sides[i] -= matchsticks[idx]; // ! backtrack
			}
		}
		return false;
	}

	private static boolean allSidesEqual(int[] arr) {
		return IntStream.range(1, arr.length).allMatch(i -> arr[i] == arr[0]);
	}

	static void main() {
		var matchsticksToSquare = new MatchsticksToSquare();
		println(matchsticksToSquare.makesquare(new int[] {1, 1, 2, 2, 2})); // true
		println(matchsticksToSquare.makesquare(new int[] {3, 3, 3, 3, 4})); // false
		println(
				matchsticksToSquare.makesquare(
						new int[] {5, 5, 5, 5, 16, 4, 4, 4, 4, 4, 3, 3, 3, 3, 4})); // false
		println(
				matchsticksToSquare.makesquare(
						new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 102})); // false
	}
}
