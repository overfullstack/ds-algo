package practice.dp;

/* 31 Aug 2025 14:17 */

/** [Stone Game III](https://leetcode.com/problems/stone-game-iii/) */
public class StoneGame3 {
	public String stoneGameIII(int[] stoneValue) {
		var stoneDiffForFirstTurn = maxStoneDiffForTurn(0, stoneValue, new int[stoneValue.length]);
		if (stoneDiffForFirstTurn > 0) {
			return "Alice";
		} else if (stoneDiffForFirstTurn < 0) {
			return "Bob";
		} else {
			return "Tie";
		}
	}

	private static int maxStoneDiffForTurn(int startIdx, int[] stoneValue, int[] memo) {
		if (startIdx >= stoneValue.length) {
			return 0;
		}
		if (memo[startIdx] != 0) {
			return memo[startIdx];
		}
		var stoneDiff = stoneValue[startIdx] - maxStoneDiffForTurn(startIdx + 1, stoneValue, memo);
		if (startIdx + 1 < stoneValue.length) {
			stoneDiff =
					Math.max(
							stoneDiff,
							stoneValue[startIdx]
									+ stoneValue[startIdx + 1]
									- maxStoneDiffForTurn(startIdx + 2, stoneValue, memo));
		}
		if (startIdx + 2 < stoneValue.length) {
			stoneDiff =
					Math.max(
							stoneDiff,
							stoneValue[startIdx]
									+ stoneValue[startIdx + 1]
									+ stoneValue[startIdx + 2]
									- maxStoneDiffForTurn(startIdx + 3, stoneValue, memo));
		}
		memo[startIdx] = stoneDiff;
		return stoneDiff;
	}

	static void main() {
		var stoneGame3 = new StoneGame3();
		System.out.println(stoneGame3.stoneGameIII(new int[] {1, 2, 3, 7}));
		System.out.println(stoneGame3.stoneGameIII(new int[] {1, 2, 3, -9}));
		System.out.println(stoneGame3.stoneGameIII(new int[] {1, 2, 3, 6}));
	}
}
