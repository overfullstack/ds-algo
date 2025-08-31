package practice;

import java.util.Arrays;

/** [1140. Stone Game II](https://leetcode.com/problems/stone-game-ii/) */
public class StoneGame2 {
	public int stoneGameII(int[] piles) {
		var suffixSum = Arrays.copyOf(piles, piles.length);
		for (var i = suffixSum.length - 2; i >= 0; i--) {
			suffixSum[i] += suffixSum[i + 1];
		}
		return maxStonesForTurn(suffixSum, 0, 1, new int[piles.length][piles.length]);
	}

	// * This builds from last to first, so your outcome can be derived from opponent's max outcome.
	// * This is a DFS where we explore all possible paths with the `x` loop
	private static int maxStonesForTurn(int[] suffixSum, int startIdx, int m, int[][] memo) {
		if (startIdx + 2 * m - 1 >= suffixSum.length - 1) {
			return suffixSum[startIdx]; // ! All stones in `startIdx..lastIndex`
		}
		if (memo[startIdx][m] != 0) {
			return memo[startIdx][m];
		}
		var maxStones = 0;
		for (var x = 1; x <= 2 * m; x++) { // ! Pick one or more including startIdx
			maxStones =
					Math.max(
							maxStones,
							// ! (Total stones left from `startIdx..lastIndex`) - (Max for opponent's turn)
							suffixSum[startIdx]
									- maxStonesForTurn(suffixSum, startIdx + x, Math.max(m, x), memo));
		}
		memo[startIdx][m] = maxStones;
		return maxStones;
	}

	static void main() {
		var obj = new StoneGame2();
		System.out.println(obj.stoneGameII(new int[] {2, 7, 9, 4, 4}));
		System.out.println(obj.stoneGameII(new int[] {1, 2, 3, 4, 5, 100}));
	}
}
