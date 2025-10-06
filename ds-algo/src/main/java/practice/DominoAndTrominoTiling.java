package practice;

/* 06 Oct 2025 19:18 */

/** [790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling/) */
public class DominoAndTrominoTiling {
	private static final int MOD = (int) 1e9 + 7;

	public int numTilings(int n) {
		return solve(0, false, n, new int[n][2]);
	}

	private static int solve(int idx, boolean previousGaps, int n, int[][] memo) {
		if (idx > n) {
			return 0;
		}
		var prevGapsVal = previousGaps ? 0 : 1;
		if (idx == n) {
			return prevGapsVal;
		}
		if (memo[idx][prevGapsVal] != 0) {
			return memo[idx][prevGapsVal];
		}
		if (previousGaps) {
			memo[idx][prevGapsVal] =
					(solve(idx + 1, false, n, memo) + solve(idx + 1, true, n, memo)) % MOD;
		} else {
			memo[idx][prevGapsVal] =
					(int)
							((solve(idx + 1, false, n, memo)
													+ solve(idx + 2, false, n, memo) // ! Only 1 new way, horizontal `=`
													// ! `2L * ` like binary digits, extra bit increases that combination by 2x
													+ 2L * solve(idx + 2, true, n, memo)) // ! 2 new ways with trominos
											% MOD);
		}
		return memo[idx][prevGapsVal];
	}

	static void main() {
		var obj = new DominoAndTrominoTiling();
		System.out.println(obj.numTilings(3)); // 5
		System.out.println(obj.numTilings(1)); // 1
		System.out.println(obj.numTilings(30)); // 312342182
	}
}
