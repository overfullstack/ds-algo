package practice.backtracking;

import static java.lang.IO.println;

import java.util.Arrays;
import java.util.stream.IntStream;

/* 18 Oct 2025 14:16 */

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

	private static boolean solve(int idx, int[] acc, int sideLen, int[] matchsticks) {
		if (idx == matchsticks.length) {
			return acc[0] == sideLen && acc[1] == sideLen && acc[2] == sideLen && acc[3] == sideLen;
		}
		for (var i = 0; i < 4; i++) {
			if (acc[i] + matchsticks[idx] <= sideLen) {
				acc[i] += matchsticks[idx];
				final var result = solve(idx + 1, acc, sideLen, matchsticks);
				if (result) {
					return true;
				}
				acc[i] -= matchsticks[idx];
			}
		}
		return false;
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
