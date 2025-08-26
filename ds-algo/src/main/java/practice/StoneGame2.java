package practice;

import java.util.Arrays;

/**
 * [1140. Stone Game II](https://leetcode.com/problems/stone-game-ii/)
 */
public class StoneGame2 {
	public int stoneGameII(int[] piles) {
		var prefixSum = Arrays.copyOf(piles, piles.length);
		for (var i = prefixSum.length - 2; i >= 0; i--) {
			prefixSum[i] += prefixSum[i + 1];
		}
		return maxStones(prefixSum, 0, 1, new int[piles.length][piles.length]);
	}

  // * This builds from last to first, so your outcome can be derived from opponent's max outcome.
  // * This is a DFS where we explore all possible paths with the `x` loop 
	private static int maxStones(int[] prefixSum, int startIdx, int m, int[][] memo) {
		if (startIdx + 2 * m - 1 >= prefixSum.length - 1) {
			return prefixSum[startIdx]; // All stones including startIdx
		}
		if (memo[startIdx][m] != 0) {
			return memo[startIdx][m];
		}
		var maxStones = 0;
		for (var x = 1; x <= 2 * m; x++) { // ! Pick one or more including startIdx
			var pickStonesFromXPiles = prefixSum[startIdx] - prefixSum[startIdx + x]; 
			maxStones =
					Math.max(
							maxStones,
							pickStonesFromXPiles
              // ! If you picked `x`, left over stones after opponent's picked max for his turn
              // ! starting with `startIdx + x` and `m = max(m, x) - problem specific`
									+ prefixSum[startIdx + x]
									- maxStones(prefixSum, startIdx + x, Math.max(m, x), memo)); // ! Max for opponent
		}
		memo[startIdx][m] = maxStones;
		return maxStones;
	}

  static void main() {
    var obj = new StoneGame2();
    System.out.println(obj.stoneGameII(new int[]{2, 7, 9, 4, 4}));
    System.out.println(obj.stoneGameII(new int[]{1,2,3,4,5,100}));
  }
}
